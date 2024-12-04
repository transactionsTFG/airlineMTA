package generatemocks;

import org.junit.BeforeClass;

import business.flight.SAAFlight;
import business.flight.SAAFlightImpl;
import business.reservation.SAAReservation;
import business.reservation.SAAReservationImpl;

public class SAManagerForUnitTest {
	protected static SAAFlight SERVICE_FLIGHT;
	protected static SAAReservation SERVICE_RESERVATION;
	protected static GenerateEntityForUnitTest GENERATE_ENTITY_MOCK;
	
	@BeforeClass
	public static void setUp() {
		GENERATE_ENTITY_MOCK = GenerateEntityForUnitTest.init();
		SERVICE_FLIGHT = new SAAFlightImpl();
		SERVICE_RESERVATION = new SAAReservationImpl();
	}
	
}
