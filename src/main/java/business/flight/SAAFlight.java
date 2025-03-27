package business.flight;

import java.util.List;

import business.flightinstance.FlightInstanceDTO;
import common.dto.flight.FlightData;
import common.dto.result.Result;

public interface SAAFlight {
    Result<FlightDTO> search(long idFlight);
    List<FlightData> searchWithParams(final String countryOrigin, final String countryDestination, final String cityOrigin, final String cityDestination, final String dateOrigin);
    FlightInstanceDTO searchReservationFlightInstance(long idFlightInstance);
}
