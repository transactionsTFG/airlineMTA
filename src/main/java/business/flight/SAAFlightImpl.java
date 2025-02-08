package business.flight;

import common.consts.SAError;
import common.dto.result.Result;
import common.exception.SAAFlightException;
import common.exception.SAException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Stateless //Agrego esto para que se pueda gestionar mediante un contenedor de EJB
public class SAAFlightImpl implements SAAFlight {
		
	private final EntityManager em;

	@Inject
	public SAAFlightImpl(final EntityManager em){
		this.em = em;
	}

	@Override
	public Result<FlightDTO> search(long idFlight) throws SAException {
		Flight flight = this.em.find(Flight.class, idFlight, LockModeType.OPTIMISTIC);
		if (flight == null) 
			throw new SAAFlightException(SAError.FLIGHT_DONTFOUND);
			
		return Result.success(flight.toDto());
	}
}
