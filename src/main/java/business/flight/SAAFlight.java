package business.flight;

import common.dto.result.Result;

public interface SAAFlight {
    Result<FlightDTO> search(long idFlight);
}
