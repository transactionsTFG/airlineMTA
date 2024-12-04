package business.flight;

import business.consts.SAError;
import business.exception.SAAFlightException;
import business.result.Result;
import integration.transaction.TransactionManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

public class SAAFlightImpl implements SAAFlight {

	@Override
	public Result<FlightDTO> search(long idFlight) {
		Result<FlightDTO> flightDTO = null;
		try {
			TransactionManager.getInstancia().nuevaTransaccion().start();
			EntityManager em = (EntityManager) TransactionManager.getInstancia().getTransaccion().getResource();
			Flight flight = em.find(Flight.class, idFlight, LockModeType.OPTIMISTIC);
			if (flight == null) 
				throw new SAAFlightException(SAError.FLIGHT_DONTFOUND);
			TransactionManager.getInstancia().getTransaccion().commit();
			flightDTO = Result.success(flight.toDto());
		} catch (Exception e) {
			flightDTO = Result.failure(e.getMessage());
			TransactionManager.getInstancia().getTransaccion().rollback();
		}
		
		return flightDTO;
	}
}
