package business.reservationline;

import java.io.Serializable;

import business.flightinstance.FlightInstance;
import business.reservation.Reservation;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Version;

@Entity
@NamedQueries({
	@NamedQuery(name = "business.reservationline.ReservationLine.findByFlightAndReservation",
				query = "SELECT rl FROM ReservationLine rl WHERE rl.reservation.id = :idReservation AND rl.flightInstance.id = :idFlightInstance"),	
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
	
	@Version
	private int version;
	public ReservationLine() {}
	
	public ReservationLine(FlightInstance flightInstance, Reservation reservation, int passengerCount) {
		super();
		this.flightInstance = flightInstance;
		this.reservation = reservation;
		this.passengerCount = passengerCount;
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
	
	public ReservationLineDTO toDto() {
		return new ReservationLineDTO(this.flightInstance.getId(), this.reservation.getId(), passengerCount);
	}
	
}