package generatemocks;

import business.aircraft.Aircraft;
import business.aircraft.AircraftDTO;
import business.customer.Customer;
import business.customer.CustomerDTO;
import business.flight.Flight;
import business.flight.FlightDTO;
import business.flightinstance.FlightInstance;
import business.flightinstance.FlightInstanceDTO;
import business.reservation.Reservation;
import business.reservation.ReservationDTO;
import business.reservationline.ReservationLine;
import business.reservationline.ReservationLineDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;

public class ConsultMocks {
	private final EntityManager em;
	
	public ConsultMocks(EntityManager em) {
		this.em = em;
	}
	
	public CustomerDTO getCustomerMockFirst() {
		TypedQuery<Customer> query = em.createNamedQuery("business.customer.Customer.getAll", Customer.class);
		query.setMaxResults(1);
		Customer r = query.getResultStream().findFirst().orElse(null);
		return r.toDto();
	}
	
	public ReservationDTO getReservationMockFirst() {
		TypedQuery<Reservation> query = em.createNamedQuery("business.reservation.Reservation.getAll", Reservation.class);
		query.setMaxResults(1);
		Reservation r = query.getResultStream().findFirst().orElse(null);
		return r.toDto();
	}
	
	public AircraftDTO getAircraftMockFirst() {
		TypedQuery<Aircraft> query = em.createNamedQuery("business.aircraft.Aircraft.getAll", Aircraft.class);
		query.setMaxResults(1);
		Aircraft a = query.getResultStream().findFirst().orElse(null);
		return a.toDto();
	}
	
	public FlightDTO getFlightMockFirst() {
		TypedQuery<Flight> query = em.createNamedQuery("business.flight.Flight.getAll", Flight.class);
		query.setMaxResults(1);
		Flight f = query.getResultStream().findFirst().orElse(null);
		return f.toDto();
	}
	
	public FlightInstanceDTO getFlightInstanceMockFirst() {
		TypedQuery<FlightInstance> query = em.createNamedQuery("business.flightinstance.FlightInstance.getAll", FlightInstance.class);
		query.setMaxResults(1);
		FlightInstance f = query.getResultStream().findFirst().orElse(null);
		return f.toDto();
	}
	
	public FlightInstanceDTO getFlightInstanceMockById(final long id) {
		FlightInstance f = em.find(FlightInstance.class, id, LockModeType.OPTIMISTIC);
		return f.toDto();
	}
	
	public ReservationDTO getReservationMockById(final long id) {
		Reservation reservation = em.find(Reservation.class, id);
		return reservation.toDto();
	}
	
	public ReservationLineDTO reservationLineMockByIdFlightAndIdReservation() {
		TypedQuery<ReservationLine> query = em.createNamedQuery("business.reservationline.ReservationLine.findAll", ReservationLine.class);
		query.setMaxResults(1);
		ReservationLine r = query.getResultStream().findFirst().orElse(null);
		return r.toDto();
	}
}
