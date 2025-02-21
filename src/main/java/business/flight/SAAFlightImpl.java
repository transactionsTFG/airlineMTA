package business.flight;

import common.consts.SAError;
import common.dto.flight.FlightData;
import common.dto.result.Result;
import common.exception.SAAFlightException;
import common.exception.SAException;
import common.utils.ZonedDateUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import business.flightinstance.FlightInstance;

@Stateless //Agrego esto para que se pueda gestionar mediante un contenedor de EJB
public class SAAFlightImpl implements SAAFlight {
		
	private EntityManager em;

	public SAAFlightImpl(){}

	@Inject
	public SAAFlightImpl(final EntityManager em){
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
	public List<FlightData> searchWithParams(String countryOrigin, String countryDestination, String cityOrigin, String cityDestination, String dateOrigin) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<FlightData> cq = cb.createQuery(FlightData.class);
		Root<Flight> flight = cq.from(Flight.class);
		Join<Flight, FlightInstance> flightInstance = flight.join("flightInstance", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.equal(flight.get("active"), true));
		
		if (countryOrigin != null && !countryOrigin.isEmpty()) 
			predicates.add(cb.equal(flight.get("origin").get("country"), countryOrigin));
	
		if (countryDestination != null && !countryDestination.isEmpty()) 
			predicates.add(cb.equal(flight.get("destination").get("country"), countryDestination));
	
		if (cityOrigin != null && !cityOrigin.isEmpty()) 
			predicates.add(cb.equal(flight.get("origin").get("city"), cityOrigin));
	
		if (cityDestination != null && !cityDestination.isEmpty()) 
			predicates.add(cb.equal(flight.get("destination").get("city"), cityDestination));
	
		if (dateOrigin != null && !dateOrigin.isEmpty()) {
			Result<ZonedDateTime> dateOriginZoned = ZonedDateUtils.getZonedTime(dateOrigin);
			if (dateOriginZoned.isSuccess()) {
				predicates.add(cb.greaterThanOrEqualTo(flightInstance.get("departureDate"), dateOriginZoned.getData())); 
			} else {
				throw new SAAFlightException("Formato de fecha inválido: " + dateOrigin);
			}
		}
	
		cq.select(cb.construct(FlightData.class, 
			flight.get("id"),
			flightInstance.get("arrivalDate"),
    		flightInstance.get("departureDate"),
			flight.get("destination").get("city"),
			flight.get("origin").get("country"),
			flight.get("destination").get("country"),
			flight.get("weekDay"))
		).where(predicates.toArray(new Predicate[0]));


        return em
				.createQuery(cq)
				.getResultList();
	}
}
