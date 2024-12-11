package business;

import java.time.ZoneId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc1, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc2, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc3, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc4, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc5, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc6, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc7, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc8, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc9, null, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(badc10, null, 0, 0));
	}
	
	@Test
	public void makeBadReservationTest() throws SAException {
		CustomerDTO c = GET_MOCK.getCustomerMockFirst();
		String goodDate = "28/11/2024 10:30:00 " + ZoneId.systemDefault();
		String badDate = "28/11/2024 10:30:00";
		ReservationDTO reservationDTO1 = new ReservationDTO(badDate, 100, true);
		ReservationDTO reservationDTO2 = new ReservationDTO(null, 100, true);
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, reservationDTO1, 0, 0));
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, reservationDTO2, 0, 0));

		
		ReservationDTO reservationDTO3 = new ReservationDTO(goodDate, 100, true);
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, reservationDTO3, -1, 0));

		
		ReservationDTO reservationDTO4 = new ReservationDTO(goodDate, 100, true);	
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, reservationDTO4, -1, 1));
		
		FlightInstanceDTO flight1 = GET_MOCK.getFlightInstanceMockFirst();
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.make(c, reservationDTO4, flight1.getId(), 99999999));
	}
	
	@Test
	public void makeReservationTest() throws SAException {
		CustomerDTO c = GET_MOCK.getCustomerMockFirst();
		ReservationDTO r = new ReservationDTO("28/11/2024 10:30:00 " + ZoneId.systemDefault(), 100, true);
		FlightInstanceDTO flight = GET_MOCK.getFlightInstanceMockFirst();
		int seats = 2;
		Result<ReservationDTO> result = SERVICE_RESERVATION.make(c, r, flight.getId(), seats);
		assertNotNull(result.getData());
		ReservationDTO reser = result.getData();
		assertTrue(reser.getId() > 0);
		assertEquals(reser.getIdCustomer(), c.getId());
		assertTrue(reser.getTotal() == r.getTotal());
		FlightInstanceDTO flightAfterReservation = GET_MOCK.getFlightInstanceMockById(flight.getId());
		super.commit();
		assertEquals(flight.getPassengerCounter() - seats, flightAfterReservation.getPassengerCounter());
	}
	
	@Test
	public void modifyReservationTest() throws SAException {
		ReservationLineDTO reservationLineDto = GET_MOCK.reservationLineMockByIdFlightAndIdReservation();
		ReservationDTO reservationDto = GET_MOCK.getReservationMockById(reservationLineDto.getReservationId());
		ReservationDTO copy = reservationDto;
		copy.setDate("Fecha Incorrecta");
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.modify(reservationDto, reservationLineDto));
		copy.setDate("29/11/2024 10:30:00 Europe/London");
		copy.setIdCustomer(-1);
		assertThrows(SAException.class, () -> SERVICE_RESERVATION.modify(reservationDto, reservationLineDto));
		
		ReservationDTO reservationDto2 = GET_MOCK.getReservationMockById(reservationLineDto.getReservationId());
		FlightInstanceDTO flight = GET_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		reservationLineDto.setPassengerCount(flight.getPassengerCounter() + reservationLineDto.getPassengerCount());
		SERVICE_RESERVATION.modify(reservationDto2, reservationLineDto);
		FlightInstanceDTO flightAfterMod = GET_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		super.commit();
		assertEquals(0, flightAfterMod.getPassengerCounter());		
	}
	
	@Test
	public void cancelTest() throws SAException {
		ReservationLineDTO reservationLineDto = GET_MOCK.reservationLineMockByIdFlightAndIdReservation();
		FlightInstanceDTO flightBeforeCancel = GET_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		Result<Void> cancel = SERVICE_RESERVATION.cancel(reservationLineDto.getReservationId(), reservationLineDto.getFlightInstanceId());
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
