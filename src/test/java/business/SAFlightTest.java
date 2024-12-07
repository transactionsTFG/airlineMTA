package business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import business.flight.FlightDTO;
import common.consts.SAError;
import common.consts.SASuccess;
import common.dto.result.Result;
import generatemocks.SAManagerForUnitTest;

public class SAFlightTest extends SAManagerForUnitTest {
		
	@Test
	public void searchTest() {
		FlightDTO flightDto = GENERATE_ENTITY_MOCK.getFlightMockFirst();
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
	public void searchDontFoundTest() {
		Result<FlightDTO> result = SERVICE_FLIGHT.search(-1);
		assertEquals(result.getMessage(), SAError.FLIGHT_DONTFOUND);
		assertFalse(result.isSuccess());
		assertNull(result.getData());
	}
	
	
}
