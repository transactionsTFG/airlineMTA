package business.flightinstance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import business.flight.Flight;
import business.reservationline.ReservationLine;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;



@Entity
@NamedQueries({
	@NamedQuery(name = "business.flightinstance.FlightInstance.getAll",
				query = "SELECT f FROM FlightInstance f"),
	@NamedQuery(name = "business.flightinstance.FlightInstance.ById",
				query = "SELECT f FROM FlightInstance f WHERE f.id = :id")

})
@AllArgsConstructor
public class FlightInstance implements Serializable {
	
	private static final long serialVersionUID = 4017621675253975021L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private LocalDate departureDate;
	@Column(nullable = false)
	private LocalDate arrivalDate;
	@Column(nullable = false)
	private String statusFlight;
	private int passengerCounter;
	@Column(nullable = false)
	private double price;
	private boolean active;
	@ManyToOne(optional = false)
	private Flight flight;
	
	@OneToMany(mappedBy = "flightInstance")
	private Set<ReservationLine> reservationLine;
	
	@Version
	private int version;
	
	public FlightInstance() {}
	
	public FlightInstance(LocalDate departureDate, LocalDate arrivalDate, String statusFlight,
			int passengerCounter, boolean active, Flight flight) {
		super();
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.statusFlight = statusFlight;
		this.passengerCounter = passengerCounter;
		this.active = active;
		this.flight = flight;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getStatusFlight() {
		return statusFlight;
	}

	public void setStatusFlight(String statusFlight) {
		this.statusFlight = statusFlight;
	}

	public int getPassengerCounter() {
		return passengerCounter;
	}

	public void setPassengerCounter(int passengerCounter) {
		this.passengerCounter = passengerCounter;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public Set<ReservationLine> getReservationLine() {
		return reservationLine;
	}

	public void setReservationLine(Set<ReservationLine> reservationLine) {
		this.reservationLine = reservationLine;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public FlightInstanceDTO toDto() {
		return new FlightInstanceDTO(this.id, 
									this.arrivalDate, 
									this.departureDate, 
									 statusFlight, passengerCounter, this.getFlight().getId(), active, price);
	}
}