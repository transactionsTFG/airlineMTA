package business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.ZoneId;

import org.junit.Test;

import business.consts.SAError;
import business.consts.SASuccess;
import business.consts.ValidatorMessage;
import business.customer.CustomerDTO;
import business.flight.FlightDTO;
import business.flightinstance.FlightInstanceDTO;
import business.reservation.ReservationDTO;
import business.reservationline.ReservationLineDTO;
import business.result.Result;
import generatemocks.SAManagerForUnitTest;

public class SAReservationTest extends SAManagerForUnitTest {
	@Test
	public void makeBadCustomerTest() {
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
		Result<ReservationDTO> badResult1 = SERVICE_RESERVATION.make(badc1, null, 0, 0);
		Result<ReservationDTO> badResult2 = SERVICE_RESERVATION.make(badc2, null, 0, 0);
		Result<ReservationDTO> badResult3 = SERVICE_RESERVATION.make(badc3, null, 0, 0);
		Result<ReservationDTO> badResult4 = SERVICE_RESERVATION.make(badc4, null, 0, 0);
		Result<ReservationDTO> badResult5 = SERVICE_RESERVATION.make(badc5, null, 0, 0);
		Result<ReservationDTO> badResult6 = SERVICE_RESERVATION.make(badc6, null, 0, 0);
		Result<ReservationDTO> badResult7 = SERVICE_RESERVATION.make(badc7, null, 0, 0);
		Result<ReservationDTO> badResult8 = SERVICE_RESERVATION.make(badc8, null, 0, 0);
		Result<ReservationDTO> badResult9 = SERVICE_RESERVATION.make(badc9, null, 0, 0);
		Result<ReservationDTO> badResult10 = SERVICE_RESERVATION.make(badc10, null, 0, 0);
		assertEquals(ValidatorMessage.BAD_NAME, badResult1.getMessage());
		assertEquals(ValidatorMessage.BAD_NAME, badResult2.getMessage());
		assertEquals(ValidatorMessage.BAD_EMAIL, badResult3.getMessage());
		assertEquals(ValidatorMessage.BAD_EMAIL, badResult4.getMessage());
		assertEquals(ValidatorMessage.BAD_EMAIL, badResult5.getMessage());
		assertEquals(ValidatorMessage.BAD_PHONE, badResult6.getMessage());
		assertEquals(ValidatorMessage.BAD_PHONE, badResult7.getMessage());
		assertEquals(ValidatorMessage.BAD_DNI, badResult8.getMessage());
		assertEquals(ValidatorMessage.BAD_DNI, badResult9.getMessage());
		assertEquals(ValidatorMessage.BAD_DNI, badResult10.getMessage());
	}
	
	@Test
	public void makeBadReservationTest() {
		CustomerDTO c = GENERATE_ENTITY_MOCK.getCustomerMockFirst();
		String goodDate = "28/11/2024 10:30:00 " + ZoneId.systemDefault();
		String badDate = "28/11/2024 10:30:00";
		ReservationDTO reservationDTO1 = new ReservationDTO(badDate, 100, true);
		ReservationDTO reservationDTO2 = new ReservationDTO(null, 100, true);
		Result<ReservationDTO> badResult1 = SERVICE_RESERVATION.make(c, reservationDTO1, 0, 0);
		Result<ReservationDTO> badResult2 = SERVICE_RESERVATION.make(c, reservationDTO2, 0, 0);
		assertEquals(ValidatorMessage.BAD_TIME, badResult1.getMessage());
		assertEquals(ValidatorMessage.BAD_TIME, badResult2.getMessage());
		
		ReservationDTO reservationDTO3 = new ReservationDTO(goodDate, 100, true);
		Result<ReservationDTO> badResult3 = SERVICE_RESERVATION.make(c, reservationDTO3, -1, 0);
		assertEquals(SAError.FLIGHT_BUY_SEATS, badResult3.getMessage());		
	
		ReservationDTO reservationDTO4 = new ReservationDTO(goodDate, 100, true);
		Result<ReservationDTO> badResult4 = SERVICE_RESERVATION.make(c, reservationDTO4, -1, 1);
		assertEquals(SAError.FLIGHT_INSTANCE_DONTFOUND, badResult4.getMessage());
		
		FlightInstanceDTO flight1 = GENERATE_ENTITY_MOCK.getFlightInstanceMockFirst();
		Result<ReservationDTO> badResult5 = SERVICE_RESERVATION.make(c, reservationDTO4, flight1.getId(), 99999999);
		assertEquals(SAError.FLIGHT_SEATS_FULL, badResult5.getMessage());	
	}
	
	@Test
	public void makeReservationTest() {
		CustomerDTO c = GENERATE_ENTITY_MOCK.getCustomerMockFirst();
		ReservationDTO r = new ReservationDTO("28/11/2024 10:30:00 " + ZoneId.systemDefault(), 100, true);
		FlightInstanceDTO flight = GENERATE_ENTITY_MOCK.getFlightInstanceMockFirst();
		int seats = 2;
		Result<ReservationDTO> result = SERVICE_RESERVATION.make(c, r, flight.getId(), seats);
		assertNotNull(result.getData());
		ReservationDTO reser = result.getData();
		assertTrue(reser.getId() > 0);
		assertEquals(reser.getIdCustomer(), c.getId());
		assertTrue(reser.getTotal() == r.getTotal());
		FlightInstanceDTO flightAfterReservation = GENERATE_ENTITY_MOCK.getFlightInstanceMockById(flight.getId());
		assertEquals(flight.getPassengerCounter() - seats, flightAfterReservation.getPassengerCounter());
	}
	
	@Test
	public void modifyReservationTest() {
		ReservationLineDTO reservationLineDto = GENERATE_ENTITY_MOCK.reservationLineMockByIdFlightAndIdReservation();
		ReservationDTO reservationDto = GENERATE_ENTITY_MOCK.getReservationMockById(reservationLineDto.getReservationId());
		ReservationDTO copy = reservationDto;
		copy.setDate("Fecha Incorrecta");
		Result<ReservationDTO> reservationBad1 = SERVICE_RESERVATION.modify(reservationDto, reservationLineDto);
		assertNull(reservationBad1.getData());
		copy.setDate("29/11/2024 10:30:00 Europe/London");
		copy.setIdCustomer(-1);
		Result<ReservationDTO> reservationBad2 = SERVICE_RESERVATION.modify(reservationDto, reservationLineDto);
		assertEquals(reservationBad2.getMessage(), SAError.CUSTOMER_DONTFOUND);
		
		ReservationDTO reservationDto2 = GENERATE_ENTITY_MOCK.getReservationMockById(reservationLineDto.getReservationId());
		FlightInstanceDTO flight = GENERATE_ENTITY_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		reservationLineDto.setPassengerCount(flight.getPassengerCounter() + reservationLineDto.getPassengerCount());
		SERVICE_RESERVATION.modify(reservationDto2, reservationLineDto);
		FlightInstanceDTO flightAfterMod = GENERATE_ENTITY_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		assertEquals(0, flightAfterMod.getPassengerCounter());		
	}
	
	@Test
	public void cancelTest() {
		ReservationLineDTO reservationLineDto = GENERATE_ENTITY_MOCK.reservationLineMockByIdFlightAndIdReservation();
		FlightInstanceDTO flightBeforeCancel = GENERATE_ENTITY_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		Result<Void> cancel = SERVICE_RESERVATION.cancel(reservationLineDto.getReservationId(), reservationLineDto.getFlightInstanceId());
		assertTrue(cancel.isSuccess());
		FlightInstanceDTO flightAfterCancel = GENERATE_ENTITY_MOCK.getFlightInstanceMockById(reservationLineDto.getFlightInstanceId());
		assertEquals(flightAfterCancel.getPassengerCounter(), flightBeforeCancel.getPassengerCounter() + reservationLineDto.getPassengerCount());
	}
	
	

	@Test
	public void readTest() {
		ReservationDTO reservationDto = GENERATE_ENTITY_MOCK.getReservationMockFirst();
		Result<ReservationDTO> result = SERVICE_RESERVATION.read(reservationDto.getId());
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
	public void dontFoundReservationTest() {
		Result<ReservationDTO> result = SERVICE_RESERVATION.read(-1);
		assertEquals(result.getMessage(), SAError.RESERVATION_DONTFOUND);
		assertFalse(result.isSuccess());
		assertNull(result.getData());
	}
}
