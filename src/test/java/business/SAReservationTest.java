package business;

import java.time.ZoneId;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import business.customer.CustomerDTO;
import business.flightinstance.FlightInstanceDTO;
import business.reservation.ReservationDTO;
import business.reservationline.ReservationLineDTO;
import common.consts.SASuccess;
import common.dto.result.Result;
import common.exception.SAException;
import generatemocks.SAManagerForUnitTest;

public class SAReservationTest extends SAManagerForUnitTest {
	@Test
	public void makeBadCustomerTest() throws SAException {
		CustomerDTO badc1 = new CustomerDTO(null, "email", "phone", "dni", true);
		CustomerDTO badc2 = new CustomerDTO("", "email", "phone", "dni", true);
		CustomerDTO badc3 = new CustomerDTO("jose", "jose@", "phone", "dni", true);
		CustomerDTO badc4 = new CustomerDTO("jose", "jose@email", "phone", "dni", true);
		CustomerDTO badc5 = new CustomerDTO("jose", null, "phone", "dni", true);
		CustomerDTO badc6 = new CustomerDTO("jose", "jose@email.com", "X", "dni", true);
		CustomerDTO badc7 = new CustomerDTO("jose", "jose@email.com", "12345", "dni", true);
		CustomerDTO badc8 = new CustomerDTO("jose", "jose@email.com", "123456789", "dni", true);
		CustomerDTO badc9 = new CustomerDTO("jose", "jose@email.com", "123456789", "5744081XK", true);
		CustomerDTO badc10 = new CustomerDTO("jose", "jose@email.com", "123456789", "574408191", true);
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc1, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc2, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc3, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc4, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc5, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc6, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc7, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc8, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc9, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc10, new HashMap<>()));
	}
	
	@Test
	public void makeBadReservationTest() throws SAException {
		CustomerDTO c = GET_MOCK.getCustomerMockFirst();
		String goodDate = "28/11/2024 10:30:00 " + ZoneId.systemDefault();
		String badDate = "28/11/2024 10:30:00";
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, new HashMap<>()));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, new HashMap<>()));

		
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, new HashMap<>()));

		
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, new HashMap<>()));
		
		FlightInstanceDTO flight1 = GET_MOCK.getFlightInstanceMockFirst();
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, new HashMap<>()));
	}

	@Test
	public void cancelTest() throws SAException {
		ReservationLineDTO reservationLineDto = GET_MOCK.reservationLineMockByIdFlightAndIdReservation();
		FlightInstanceDTO flightBeforeCancel = GET_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		Result<Double> cancel = SERVICE_RESERVATION.cancel(1);
		assertTrue(cancel.isSuccess());
		FlightInstanceDTO flightAfterCancel = GET_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		super.commit();
		assertEquals(flightAfterCancel.getPassengerCounter(), flightBeforeCancel.getPassengerCounter() + reservationLineDto.getPassengerCount());
	}
	
	

	@Test
	public void readTest() throws SAException {
		ReservationDTO reservationDto = GET_MOCK.getReservationMockFirst();
		Result<ReservationDTO> result = SERVICE_RESERVATION.read(reservationDto.getId());
		super.commit();
		assertEquals(result.getMessage(), SASuccess.GENERIC);
		assertTrue(result.isSuccess());
		ReservationDTO reservationResult = result.getData();
		assertEquals(reservationDto.getId(), reservationResult.getId());
		assertEquals(reservationDto.isActive(), reservationResult.isActive());
		assertEquals(reservationDto.getIdCustomer(), reservationResult.getIdCustomer());
		assertEquals(reservationDto.getDate(), reservationResult.getDate());
		assertTrue(reservationDto.getTotal() == reservationResult.getTotal());
	}
	
	@Test
	public void dontFoundReservationTest() throws SAException {
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.read(-1));
	}
}
