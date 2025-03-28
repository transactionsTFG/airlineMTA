package business.flight;

import common.consts.SAError;
import common.dto.flight.FlightData;
import common.dto.flight.IdFlightInstanceWithSeatsDTO;
import common.dto.result.Result;
import common.exception.SAAFlightException;
import common.exception.SAException;
import common.utils.ZonedDateUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import business.airport.Airport;
import business.country.Country;
import business.flightinstance.FlightInstance;
import business.reservation.Reservation;
import business.flightinstance.FlightInstanceDTO;

@Stateless // Agrego esto para que se pueda gestionar mediante un contenedor de EJB
public class SAAFlightImpl implements SAAFlight {

    private EntityManager em;

    public SAAFlightImpl() {
    }

    @Inject
    public SAAFlightImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Result<FlightDTO> search(long idFlight) throws SAException {
        Flight flight = this.em.find(Flight.class, idFlight, LockModeType.NONE);
        if (flight == null)
            throw new SAAFlightException(SAError.FLIGHT_DONTFOUND);

        return Result.success(flight.toDto());
    }

    @Override
    public List<FlightData> searchWithParams(String countryOrigin, String countryDestination, String cityOrigin,
            String cityDestination, String dateOrigin) {
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<FlightData> cq = cb.createQuery(FlightData.class);
        Root<Flight> flight = cq.from(Flight.class);
        Join<Flight, FlightInstance> flightInstance = flight.join("flightInstance", JoinType.INNER);
        Join<Flight, Airport> originAirport = flight.join("origin", JoinType.INNER);
        Join<Flight, Airport> destinationAirport = flight.join("destination", JoinType.INNER);
        Join<Airport, Country> originCountry = originAirport.join("country", JoinType.INNER);
        Join<Airport, Country> destinationCountry = destinationAirport.join("country", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(flight.get("active"), true));

        if (countryOrigin != null && !countryOrigin.isEmpty())
            predicates.add(cb.equal(originCountry.get("name"), countryOrigin));

        if (countryDestination != null && !countryDestination.isEmpty())
            predicates.add(cb.equal(destinationCountry.get("name"), countryDestination));

        if (cityOrigin != null && !cityOrigin.isEmpty())
            predicates.add(cb.equal(originAirport.get("city"), cityOrigin));

        if (cityDestination != null && !cityDestination.isEmpty())
            predicates.add(cb.equal(destinationAirport.get("city"), cityDestination));

        if (dateOrigin != null && !dateOrigin.isEmpty()) {
            if (ZonedDateUtils.isValidateDateFilter(dateOrigin).isSuccess()) {
                Expression<LocalDate> departureDateExpr = flightInstance.get("departureDate");
                predicates.add(cb.greaterThanOrEqualTo(departureDateExpr, cb.literal(LocalDate.parse(dateOrigin))));
            } else {
                throw new SAAFlightException("Formato de fecha inválido: " + dateOrigin);
            }
        }

        cq.select(cb.construct(FlightData.class,
                flight.get("id"),
                flightInstance.get("arrivalDate"),
                flightInstance.get("departureDate"),
                destinationAirport.get("city"),
                originCountry.get("name"),
                destinationCountry.get("name"),
                flight.get("weekDay"),
                flightInstance.get("price"))).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

	@Override
	public FlightInstanceDTO searchReservationFlightInstance(long idFlightInstance) {
		FlightInstance flightInstance = this.em.find(FlightInstance.class, idFlightInstance, LockModeType.NONE);
		if (flightInstance == null) 
			throw new SAAFlightException(SAError.FLIGHT_INSTANCE_DONTFOUND);
			
		return flightInstance.toDto();
	}

    @Override
    public List<IdFlightInstanceWithSeatsDTO> searchFlightsByReservation(long idReservation) {
        Reservation reservation = this.em.find(Reservation.class, idReservation, LockModeType.OPTIMISTIC);

        if (reservation == null)
            throw new SAAFlightException(SAError.RESERVATION_DONTFOUND);

        if (!reservation.isActive())
            throw new SAAFlightException(SAError.RESERVATION_NOT_ACTIVE);

        return reservation.getReservationLine().stream()
                .map(r -> {
                    IdFlightInstanceWithSeatsDTO instanceWithSeatsDTO = new IdFlightInstanceWithSeatsDTO();
                    instanceWithSeatsDTO.setIdFlightInstance(r.getFlightInstance().getId());
                    instanceWithSeatsDTO.setSeats(r.getPassengerCount());
                    return instanceWithSeatsDTO;
                }).toList();

    }

}
