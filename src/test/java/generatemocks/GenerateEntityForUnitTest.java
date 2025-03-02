package generatemocks;

import business.aircraft.Aircraft;
import business.airport.Airport;
import business.country.Country;
import business.customer.Customer;
import business.flight.Flight;
import business.flightinstance.FlightInstance;
import business.reservation.Reservation;
import business.reservationline.ReservationLine;
import common.utils.ZonedDateUtils;
import javax.persistence.EntityManager;

public class GenerateEntityForUnitTest {
	private static GenerateEntityForUnitTest GENERATE = null;
	
	private final EntityManager em;
	
	public static synchronized GenerateEntityForUnitTest init() {
		if (GENERATE == null) 
			GENERATE = new GenerateEntityForUnitTest();
		
		return GENERATE;
	}
	
	private GenerateEntityForUnitTest() {
		this.em = SingletonEntityManagerFactory.getInstance().createEntityManager();
		this.em.getTransaction().begin();
		this.aircraftMock();
		this.airportMock();
		this.flightMock();
		this.customerMock();
		this.reservationMock();
		this.reservationLineMock();
		this.em.getTransaction().commit();
	}
	
	private void aircraftMock() {
		Aircraft aircraft1 = new Aircraft(100, "Modelo1", true);
		Aircraft aircraft2 = new Aircraft(100, "Modelo2", true);
		Aircraft aircraft3 = new Aircraft(100, "Modelo3", true);
		this.em.persist(aircraft1);
		this.em.persist(aircraft2);
		this.em.persist(aircraft3);
	}
	
	private void airportMock() {
		Country c1 = new Country();
		Airport airport1 = new Airport("Aeropuerto 1", "Ciudad", c1, "Codigo", true);
		Airport airport2 = new Airport("Aeropuerto 2", "Ciudad2", c1, "Codigo2", true);
		Airport airport3 = new Airport("Aeropuerto 3", "Ciudad3", c1, "Codigo3", true);
		this.em.persist(airport1);
		this.em.persist(airport2);
		this.em.persist(airport3);
	}
	
	private void flightMock() {
		Country c1 = new Country();
		Aircraft aircraft = new Aircraft(100, "Modelo4", true);
		Airport airport = new Airport("Aeropuerto 4", "Ciudad", c1, "Codigo", true);
		Airport airport2 = new Airport("Aeropuerto 5", "Ciudad", c1, "Codigo", true);
		Flight flight1 = new Flight("CODIGO-1", "Jueves", "2022", "2023", true, aircraft, airport, airport2);
		Flight flight2 = new Flight("CODIGO-2", "Jueves", "2023", "2024", true, aircraft, airport2, airport);
		FlightInstance flightInstace1 = new FlightInstance(ZonedDateUtils.getZonedTime("28/11/2024 10:30:00 Europe/London").getData(),
														  ZonedDateUtils.getZonedTime("29/11/2024 10:30:00 Europe/London").getData(),
														  "DISPONIBLE", 15, true, flight1);
		FlightInstance flightInstace2 = new FlightInstance(ZonedDateUtils.getZonedTime("29/11/2024 10:30:00 Europe/London").getData(),
				  ZonedDateUtils.getZonedTime("30/11/2024 10:30:00 Europe/London").getData(),
				  "DISPONIBLE", 15, true, flight2);
		this.em.persist(aircraft);
		this.em.persist(airport);
		this.em.persist(airport2);
		this.em.persist(flight1);
		this.em.persist(flight2);
		this.em.persist(flightInstace1);
		this.em.persist(flightInstace2);
	}
	
	private void customerMock() {
		Customer c1 = new Customer("Jose1", "jose1@gmail.com", "123456789", "45814881N", true);
		Customer c2 = new Customer("Jose2", "jose2@gmail.com", "123456789", "45814882N", true);
		Customer c3 = new Customer("Jose3", "jose3@gmail.com", "123456789", "45814883N", true);
		this.em.persist(c1);
		this.em.persist(c2);
		this.em.persist(c3);
	}
	
	private void reservationMock() {
		Customer c1 = new Customer("Jose4", "jose1@gmail.com", "123456789", "45814884N", true);
		Customer c2 = new Customer("Jose5", "jose2@gmail.com", "123456781", "45814885N", true);
		Reservation r1 = new Reservation(ZonedDateUtils.getZonedTime("28/11/2024 10:30:00 Europe/London").getData(), 400, true, c1);
		Reservation r2 = new Reservation(ZonedDateUtils.getZonedTime("28/12/2024 10:30:00 Europe/London").getData(), 700, true, c2);		
		this.em.persist(c1);
		this.em.persist(c2);
		this.em.persist(r1);
		this.em.persist(r2);		
	}
	
	private void reservationLineMock() {
		Country ck = new Country();
		Aircraft aircraft = new Aircraft(100, "Modelo4", true);
		Airport airport = new Airport("Aeropuerto 4", "Ciudad", ck, "Codigo", true);
		Airport airport2 = new Airport("Aeropuerto 5", "Ciudad", ck, "Codigo", true);
		Flight flight1 = new Flight("CODIGO-1", "Jueves", "2022", "2023", true, aircraft, airport, airport2);
		Flight flight2 = new Flight("CODIGO-2", "Jueves", "2023", "2024", true, aircraft, airport2, airport);
		FlightInstance flightInstace1 = new FlightInstance(ZonedDateUtils.getZonedTime("28/11/2024 10:30:00 Europe/London").getData(),
														  ZonedDateUtils.getZonedTime("29/11/2024 10:30:00 Europe/London").getData(),
														  "DISPONIBLE", 15, true, flight1);
		FlightInstance flightInstace2 = new FlightInstance(ZonedDateUtils.getZonedTime("29/11/2024 10:30:00 Europe/London").getData(),
				  ZonedDateUtils.getZonedTime("30/11/2024 10:30:00 Europe/London").getData(),
				  "DISPONIBLE", 15, true, flight2);
		Customer c1 = new Customer("Jose4", "jose1@gmail.com", "123456789", "45814884N", true);
		Customer c2 = new Customer("Jose5", "jose2@gmail.com", "123456781", "45814885N", true);
		Reservation r1 = new Reservation(ZonedDateUtils.getZonedTime("28/11/2024 10:30:00 Europe/London").getData(), 400, true, c1);
		Reservation r2 = new Reservation(ZonedDateUtils.getZonedTime("28/12/2024 10:30:00 Europe/London").getData(), 700, true, c2);		
		ReservationLine reservationLine = new ReservationLine(flightInstace1, r1, 1, 1000, true);
		ReservationLine reservationLine2 = new ReservationLine(flightInstace1, r1, 2, 1000, true);
		ReservationLine reservationLine3 = new ReservationLine(flightInstace1, r2, 1, 1000, true);
		ReservationLine reservationLine4 = new ReservationLine(flightInstace2, r2, 2, 1000, true);
		this.em.persist(aircraft);
		this.em.persist(airport);
		this.em.persist(airport2);
		this.em.persist(flight1);
		this.em.persist(flight2);
		this.em.persist(flightInstace1);
		this.em.persist(flightInstace2);
		this.em.persist(c1);
		this.em.persist(c2);
		this.em.persist(r1);
		this.em.persist(r2);	
		this.em.persist(reservationLine);
		this.em.persist(reservationLine2);
		this.em.persist(reservationLine3);
		this.em.persist(reservationLine4);
	}
	
	
}
