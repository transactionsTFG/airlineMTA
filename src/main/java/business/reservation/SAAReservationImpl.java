package business.reservation;


import java.time.ZonedDateTime;

import business.customer.Customer;
import business.customer.CustomerDTO;
import business.flightinstance.FlightInstance;
import business.reservationline.ReservationLine;
import business.reservationline.ReservationLineDTO;
import common.consts.SAError;
import common.consts.ValidatorMessage;
import common.dto.result.Result;
import common.exception.SAAFlightException;
import common.exception.SAException;
import common.exception.SAReservationException;
import common.utils.StringUtils;
import common.utils.ZonedDateUtils;
import common.validators.Validator;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Stateless
public class SAAReservationImpl implements SAAReservation {
	
	private final EntityManager em;

	@Inject
	public SAAReservationImpl(final EntityManager em){
		this.em = em;
	}

	@Override
	@Transactional
	public Result<ReservationDTO> make(CustomerDTO customerDto, final ReservationDTO reservationDto, final long idFlightInstance, final int numberOfSeats) throws SAException {
		if (StringUtils.isEmpty(customerDto.getName())) 
			throw new SAReservationException(ValidatorMessage.BAD_NAME);
		
		if (!Validator.isEmail(customerDto.getEmail())) 
			throw new SAReservationException(ValidatorMessage.BAD_EMAIL);
		
		if (!Validator.isPhone(customerDto.getPhone())) 
			throw new SAReservationException(ValidatorMessage.BAD_PHONE);
		
		if (!Validator.isDni(customerDto.getDni())) 
			throw new SAReservationException(ValidatorMessage.BAD_DNI);
		
		Result<ZonedDateTime> resultZoned = ZonedDateUtils.getZonedTime(reservationDto.getDate());
		if (!resultZoned.isSuccess()) 
			throw new SAReservationException(resultZoned.getMessage());
		
		if (0 >= numberOfSeats) 
			throw new SAReservationException(SAError.FLIGHT_BUY_SEATS);
		
		Customer customer = em.find(Customer.class, customerDto.getId(), LockModeType.OPTIMISTIC);
		if (customer == null) {
			customer = new Customer(customerDto.getName(), customerDto.getEmail(), 
									customerDto.getPhone(), customerDto.getDni(), 
									customerDto.isActive());
			em.persist(customer);
		}
		Reservation reservation = new Reservation(resultZoned.getData(), reservationDto.getTotal(), 
													reservationDto.isActive(), customer);
		em.persist(reservation);
		FlightInstance flightInstance = em.find(FlightInstance.class, idFlightInstance, LockModeType.OPTIMISTIC);
		if (flightInstance == null) 
			throw new SAReservationException(SAError.FLIGHT_INSTANCE_DONTFOUND);
		
		if (numberOfSeats > flightInstance.getPassengerCounter()) 
			throw new SAAFlightException(SAError.FLIGHT_SEATS_FULL);
		
		ReservationLine reservationLine = new ReservationLine(flightInstance, reservation, numberOfSeats);
		flightInstance.setPassengerCounter(flightInstance.getPassengerCounter() - numberOfSeats);
		em.persist(reservationLine);
		em.flush();
		return Result.success(reservation.toDto());
	}

	//TODO: Cambiar la fecha del Vuelo, es decir seria cambiar a otro vuelo
	//HPOR AHORA NO LO ESTOY TENIENDO EN CUENTA
	@Override
	@Transactional
	public Result<ReservationDTO> modify(final ReservationDTO reservationDto, final ReservationLineDTO reservationLineDto) throws SAException {
		Result<ZonedDateTime> resultZoned = ZonedDateUtils.getZonedTime(reservationDto.getDate());
		if (!resultZoned.isSuccess()) 
			throw new SAReservationException(resultZoned.getMessage());

		if (0 >= reservationLineDto.getPassengerCount()) 
			throw new SAReservationException(SAError.FLIGHT_BUY_SEATS);
		
			
		Customer customer = this.em.find(Customer.class, reservationDto.getIdCustomer(), LockModeType.OPTIMISTIC);
		if (customer == null) 
			throw new SAReservationException(SAError.CUSTOMER_DONTFOUND);
		
		Reservation reservationLast = em.find(Reservation.class, reservationDto.getId(), LockModeType.OPTIMISTIC);
		if (reservationLast == null) 
			throw new SAReservationException(SAError.RESERVATION_DONTFOUND);
			
		TypedQuery<ReservationLine> query = em.createNamedQuery("business.reservationline.ReservationLine.findByFlightAndReservation", ReservationLine.class);
		query.setParameter("idReservation", reservationDto.getId());
		query.setParameter("idFlightInstance", reservationLineDto.getFlightInstanceId());
		ReservationLine reservationLineLast = query.getResultStream().findFirst().orElse(null);
		if (reservationLineLast == null) 
			throw new SAReservationException(SAError.RESERVATION_LINE_DONTFOUND);
		
		FlightInstance flightInstance = em.find(FlightInstance.class, reservationLineDto.getFlightInstanceId(), LockModeType.OPTIMISTIC);
		if (flightInstance == null) 
			throw new SAReservationException(SAError.FLIGHT_INSTANCE_DONTFOUND);
		
		int newSeats = reservationLineDto.getPassengerCount() - reservationLineLast.getPassengerCount();
		int deleteSeats = reservationLineLast.getPassengerCount() - reservationLineDto.getPassengerCount();
		if (newSeats > 0) {
			final int totalSeats = flightInstance.getPassengerCounter() - newSeats;
			if (0 > totalSeats) 
				throw new SAReservationException(SAError.FLIGHT_SEATS_FULL);
			flightInstance.setPassengerCounter(totalSeats);	
		}
		
		if (deleteSeats > 0) 
			flightInstance.setPassengerCounter(flightInstance.getPassengerCounter() + deleteSeats);				
		
		reservationLineLast.setPassengerCount(reservationLineDto.getPassengerCount());
		reservationLast.setActive(reservationDto.isActive());
		reservationLast.setTotal(reservationDto.getTotal());
		reservationLast.setDate(resultZoned.getData());
		return Result.success(reservationLast.toDto());
	}

	@Override
	@Transactional
	public Result<Void> cancel(final long idReservation, final long idFlightInstance) throws SAException {
		Reservation reservation = em.find(Reservation.class, idReservation, LockModeType.OPTIMISTIC);
		if (reservation == null) 
			throw new SAReservationException(SAError.RESERVATION_DONTFOUND);
		
		FlightInstance flightInstance = em.find(FlightInstance.class, idFlightInstance, LockModeType.OPTIMISTIC);
		if (flightInstance == null) 
			throw new SAReservationException(SAError.FLIGHT_INSTANCE_DONTFOUND);			
		
		TypedQuery<ReservationLine> query = em.createNamedQuery("business.reservationline.ReservationLine.findByFlightAndReservation", ReservationLine.class);
		query.setParameter("idReservation", idReservation);
		query.setParameter("idFlightInstance", idFlightInstance);
		ReservationLine reservationLine = query
											.getResultStream()
											.findFirst()
											.orElseThrow(() -> new SAReservationException(SAError.RESERVATION_LINE_DONTFOUND));
		flightInstance.setPassengerCounter(flightInstance.getPassengerCounter() + reservationLine.getPassengerCount());
		em.remove(reservationLine);
		reservation.setActive(false);
		return  Result.success(null);
	}

	@Override
	@Transactional
	public Result<ReservationDTO> read(long idReservation) throws SAException {
		Reservation reservation = em.find(Reservation.class, idReservation, LockModeType.OPTIMISTIC);
		if (reservation == null) 
			throw new SAReservationException(SAError.RESERVATION_DONTFOUND);
		return Result.success(reservation.toDto());
	}
	
}
