package business.reservation;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.customer.Customer;
import business.customer.CustomerDTO;
import business.flightinstance.FlightInstance;
import business.reservationline.ReservationLine;
import common.consts.SAError;
import common.consts.ValidatorMessage;
import common.dto.reservation.NewReservationDTO;
import common.dto.reservation.StatusFlightDTO;
import common.dto.reservation.UpdatePriceReservationDTO;
import common.dto.reservation.UpdateReservationDTO;
import common.dto.result.Result;
import common.exception.SAAFlightException;
import common.exception.SAReservationException;
import common.mapper.ReservationMapper;
import common.utils.StringUtils;
import common.validators.Validator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

@Stateless
public class SAAReservationImpl implements SAAReservation {
	
	private EntityManager em;

	public SAAReservationImpl(){} //Constructor por defecto para Weblogic

	@Inject
	public SAAReservationImpl(final EntityManager em){
		this.em = em;
	}

	@Override
	public Result<NewReservationDTO> make(CustomerDTO customerDto, final Map<Long, Integer> idFlightInstanceWithSeatMap) {
		if (StringUtils.isEmpty(customerDto.getName())) 
			throw new SAReservationException(ValidatorMessage.BAD_NAME);
		
		if (!Validator.isEmail(customerDto.getEmail())) 
			throw new SAReservationException(ValidatorMessage.BAD_EMAIL);
		
		if (!Validator.isPhone(customerDto.getPhone())) 
			throw new SAReservationException(ValidatorMessage.BAD_PHONE);
		
		if (!Validator.isDni(customerDto.getDni())) 
			throw new SAReservationException(ValidatorMessage.BAD_DNI);
		
		Customer customer = em.createNamedQuery("business.customer.Customer.getCustomerByDni", Customer.class)
								.setParameter("dni", customerDto.getDni())
								.getResultList().stream().findFirst().orElse(null);
		if (customer == null) {
			customer = new Customer(customerDto.getName(), customerDto.getEmail(), 
									customerDto.getPhone(), customerDto.getDni(), 
									customerDto.isActive());
			em.persist(customer);
		}
		
		List<FlightInstance> listFlightInstance = em.createQuery("SELECT f FROM FlightInstance f WHERE f.id IN :ids", FlightInstance.class)
													.setParameter("ids", idFlightInstanceWithSeatMap.keySet())
													.getResultList(); 

		Reservation reservation = new Reservation(customer);
		em.persist(reservation);
		if (listFlightInstance.size() != idFlightInstanceWithSeatMap.size()) 
			throw new SAReservationException(SAError.FLIGHT_INSTANCE_DONTFOUND);
		
		List<StatusFlightDTO> statusList = new ArrayList<>();
		List<ReservationLine> listReservationLines = new ArrayList<>();
		double total = listFlightInstance.stream().mapToDouble(flightInstance -> {
			final int capacitySeatsAircraft = flightInstance.getFlight().getAircraft().getCapacity();
			if (capacitySeatsAircraft < flightInstance.getPassengerCounter() + idFlightInstanceWithSeatMap.get(flightInstance.getId())) 
				throw new SAAFlightException(SAError.FLIGHT_SEATS_FULL);
			
			final int numberOfSeatsForFlight = idFlightInstanceWithSeatMap.get(flightInstance.getId());
			final double totalPriceFlight = flightInstance.getPrice() * numberOfSeatsForFlight;
			flightInstance.setPassengerCounter(flightInstance.getPassengerCounter() + idFlightInstanceWithSeatMap.get(flightInstance.getId()));
			ReservationLine reservationLine = new ReservationLine(flightInstance, reservation, numberOfSeatsForFlight, totalPriceFlight, true);
			listReservationLines.add(reservationLine);
			statusList.add(new StatusFlightDTO(flightInstance.getStatusFlight(), flightInstance.getFlight().getDepartureTime(), flightInstance.getDepartureDate(), numberOfSeatsForFlight, totalPriceFlight));
			return totalPriceFlight;
		}).sum();
		reservation.setTotal(total);
		listReservationLines.stream().forEach(em::persist);
		em.flush();
		NewReservationDTO newReservationDTO = new NewReservationDTO(reservation.getId(), reservation.getDate().toString(), total, statusList);
		return Result.success(newReservationDTO);
	}

	@Override
	public Result<UpdateReservationDTO> modify(final long idReservation, final Map<Long, Integer> idFlightInstanceWithSeatsMap) {
		if (idFlightInstanceWithSeatsMap.isEmpty() || idFlightInstanceWithSeatsMap.entrySet().stream().anyMatch(entry -> entry.getValue() <= -1)) 
			throw new SAReservationException(SAError.FLIGHT_BUY_SEATS);
		
		Reservation reservationLast = em.find(Reservation.class, idReservation, LockModeType.OPTIMISTIC);
		if (reservationLast == null) 
			throw new SAReservationException(SAError.RESERVATION_DONTFOUND);
			
		TypedQuery<ReservationLine> query = em.createNamedQuery("business.reservationline.ReservationLine.findByReservation", ReservationLine.class);
		query.setParameter("idReservation", idReservation);
		
		List<ReservationLine> listReservationLines = query.getResultList();
		if (listReservationLines.isEmpty()) 
			throw new SAReservationException(SAError.RESERVATION_LINE_DONTFOUND);
		List<UpdatePriceReservationDTO> updatePriceReservationList = new ArrayList<>();
		double updatePriceReservation = listReservationLines.stream().mapToDouble(reservationLineLast -> {
			final long idFlightModify = reservationLineLast.getFlightInstance().getId();
			if (!idFlightInstanceWithSeatsMap.containsKey(idFlightModify)) 
				return 0;

			final int seatsOfIdFlightModify = idFlightInstanceWithSeatsMap.get(idFlightModify);
			if(seatsOfIdFlightModify == 0) {
				final double priceReturn = reservationLineLast.getPrice();
				final int seats = reservationLineLast.getPassengerCount();
				reservationLineLast.getFlightInstance().setPassengerCounter(reservationLineLast.getFlightInstance().getPassengerCounter() - seats);
				reservationLast.setTotal(reservationLast.getTotal() - priceReturn);
				this.em.remove(reservationLineLast);
				updatePriceReservationList.add(new UpdatePriceReservationDTO(reservationLineLast.getFlightInstance().getId(), -priceReturn));
				return -priceReturn;
			}
				
			final int newSeats = seatsOfIdFlightModify - reservationLineLast.getPassengerCount();
			final int deleteSeats = reservationLineLast.getPassengerCount() - seatsOfIdFlightModify;
			double updatePrice = 0;
			if(newSeats > 0) {
				final int totalSeats = reservationLineLast.getFlightInstance().getPassengerCounter() + newSeats;
				if (totalSeats > reservationLineLast.getFlightInstance().getFlight().getAircraft().getCapacity()) 
					throw new SAReservationException(SAError.FLIGHT_SEATS_FULL);
				final int newSeatsForFlight = reservationLineLast.getPassengerCount() + newSeats;
				final double newPriceForSeatsTotal = reservationLineLast.getFlightInstance().getPrice() * newSeatsForFlight;
				reservationLineLast.setPassengerCount(newSeatsForFlight);
				reservationLineLast.setPrice(newPriceForSeatsTotal);
				reservationLineLast.getFlightInstance().setPassengerCounter(totalSeats);
				updatePrice = reservationLineLast.getFlightInstance().getPrice() * newSeats;
				updatePriceReservationList.add(new UpdatePriceReservationDTO(reservationLineLast.getFlightInstance().getId(), newPriceForSeatsTotal));
			}

			if (deleteSeats > 0) {
				final int updateSeats = reservationLineLast.getPassengerCount() - deleteSeats;
				final double newPriceForSeatsTotal = reservationLineLast.getFlightInstance().getPrice() * updateSeats;
				reservationLineLast.getFlightInstance().setPassengerCounter(reservationLineLast.getFlightInstance().getPassengerCounter() - deleteSeats);
				reservationLineLast.setPassengerCount(updateSeats);
				reservationLineLast.setPrice(reservationLineLast.getFlightInstance().getPrice() * updateSeats);
				updatePrice = -reservationLineLast.getFlightInstance().getPrice() * deleteSeats;
				updatePriceReservationList.add(new UpdatePriceReservationDTO(reservationLineLast.getFlightInstance().getId(), newPriceForSeatsTotal));
			}
			this.em.merge(reservationLineLast);
			return updatePrice;
		}).sum();

		double total = listReservationLines.stream().mapToDouble(ReservationLine::getPrice).sum();
		reservationLast.setTotal(total);
		return Result.success(ReservationMapper.INSTANCE.toUpdateRevervationDTO(reservationLast.toDto(), updatePriceReservation, updatePriceReservationList));
	}

	@Override
	public Result<Double> cancel(final long idReservation) {
		Reservation reservation = em.find(Reservation.class, idReservation, LockModeType.OPTIMISTIC);
		if (reservation == null) 
			throw new SAReservationException(SAError.RESERVATION_DONTFOUND);

		TypedQuery<ReservationLine> query = em.createNamedQuery("business.reservationline.ReservationLine.findByReservation", ReservationLine.class);
		query.setParameter("idReservation", idReservation);
		
		List<ReservationLine> reservationLine = query.getResultList();
		if (reservationLine.isEmpty()) 
			throw new SAReservationException(SAError.RESERVATION_LINE_DONTFOUND);
		
		double price = reservationLine.stream().mapToDouble(reservationLineElement -> {
			FlightInstance flightInstance = reservationLineElement.getFlightInstance();
			final double priceFlightCancel = reservationLineElement.getPrice();
			flightInstance.setPassengerCounter(flightInstance.getPassengerCounter() - reservationLineElement.getPassengerCount());
			reservationLineElement.setActive(false);
			this.em.merge(flightInstance);
			this.em.merge(reservationLineElement);
			return priceFlightCancel;
		}).sum();
		reservation.setActive(false);
		return Result.success(price);
	}

	@Override
	public Result<ReservationDTO> read(long idReservation) {
		Reservation reservation = em.find(Reservation.class, idReservation, LockModeType.OPTIMISTIC);
		if (reservation == null) 
			throw new SAReservationException(SAError.RESERVATION_DONTFOUND);
		return Result.success(reservation.toDto());
	}
	
}
