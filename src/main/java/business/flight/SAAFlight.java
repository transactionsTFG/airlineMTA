package business.flight;

import common.dto.result.Result;
import common.exception.SAException;

public interface SAAFlight {
    Result<FlightDTO> search(long idFlight) throws SAException;
}
