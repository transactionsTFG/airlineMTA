package business.flight;

import business.result.Result;

public interface SAAFlight {
    Result<FlightDTO> search(long idFlight);
}
