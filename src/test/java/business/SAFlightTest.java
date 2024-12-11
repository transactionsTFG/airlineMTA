package business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import business.flight.FlightDTO;
import common.consts.SASuccess;
import common.dto.result.Result;
import common.exception.SAException;
import generatemocks.SAManagerForUnitTest;

public class SAFlightTest extends SAManagerForUnitTest {
		
	@Test
	public void searchTest() throws SAException {
		FlightDTO flightDto = GET_MOCK.getFlightMockFirst();
		Result<FlightDTO> result = SERVICE_FLIGHT.search(flightDto.getId());
		assertEquals(result.getMessage(), SASuccess.GENERIC);
		assertTrue(result.isSuccess());
		FlightDTO flightResult = result.getData();
		assertEquals(flightDto.getId(), flightResult.getId());
		assertEquals(flightDto.getCodeFlight(), flightResult.getCodeFlight());
		assertEquals(flightDto.getWeekDay(), flightResult.getWeekDay());
		assertEquals(flightDto.getArrivalTime(), flightResult.getArrivalTime());
		assertEquals(flightDto.getDepartureTime(), flightResult.getDepartureTime());
		assertEquals(flightDto.isActive(), flightResult.isActive());
		assertEquals(flightDto.getIdOriginFlight(), flightResult.getIdOriginFlight());
		assertEquals(flightDto.getIdDestinationFlight(), flightResult.getIdDestinationFlight());
	}
	
	@Test
	public void searchDontFoundTest() throws SAException  {
		assertThrows(SAException.class, () -> SERVICE_FLIGHT.search(-1));
	}
	
	
}
