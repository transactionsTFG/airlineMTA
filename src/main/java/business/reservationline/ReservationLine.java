package business.reservationline;

import java.io.Serializable;

import business.flightinstance.FlightInstance;
import business.reservation.Reservation;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity
@NamedQueries({
	@NamedQuery(name = "business.reservationline.ReservationLine.findByReservation",
				query = "SELECT rl FROM ReservationLine rl WHERE rl.reservation.id = :idReservation"),	
	@NamedQuery(name = "business.reservationline.ReservationLine.findAll",
				query = "SELECT rl FROM ReservationLine rl")
})
public class ReservationLine implements Serializable {
	private static final long serialVersionUID = 0;

	@EmbeddedId
	private ReservationLineIds ids = new ReservationLineIds();
	
	@ManyToOne
	@MapsId("flightInstanceId") 
	private FlightInstance flightInstance;
	
	@ManyToOne
	@MapsId("reservationId") 
	private Reservation reservation;
	
	private int passengerCount;
	
	private double price;

	private boolean active;

	@Version
	private int version;
	public ReservationLine() {}
	
	public ReservationLine(FlightInstance flightInstance, Reservation reservation, int passengerCount, double price, boolean active) {
		super();
		this.flightInstance = flightInstance;
		this.reservation = reservation;
		this.passengerCount = passengerCount;
		this.price = price;
		this.active = active;
	}
	
	public ReservationLineIds getIds() {
		return ids;
	}
	public void setIds(ReservationLineIds ids) {
		this.ids = ids;
	}
	public FlightInstance getFlightInstance() {
		return flightInstance;
	}
	public void setFlightInstance(FlightInstance flightInstance) {
		this.flightInstance = flightInstance;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public int getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public ReservationLineDTO toDto() {
		return new ReservationLineDTO(this.flightInstance.getId(), passengerCount);
	}
	
}