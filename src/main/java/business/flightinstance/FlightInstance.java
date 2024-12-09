package business.flightinstance;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

import business.flight.Flight;
import business.reservationline.ReservationLine;
import common.converter.ZonedDateTimeConverter;
import common.utils.ZonedDateUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;


@Entity
@NamedQueries({
	@NamedQuery(name = "business.flightinstance.FlightInstance.getAll",
				query = "SELECT f FROM FlightInstance f"),
	@NamedQuery(name = "business.flightinstance.FlightInstance.ById",
				query = "SELECT f FROM FlightInstance f WHERE f.id = :id")

})
public class FlightInstance implements Serializable {
	
	private static final long serialVersionUID = 4017621675253975021L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	@Convert(converter = ZonedDateTimeConverter.class)
	private ZonedDateTime departureDate;
	@Column(nullable = false)
	@Convert(converter = ZonedDateTimeConverter.class)
	private ZonedDateTime arrivalDate;
	@Column(nullable = false)
	private String statusFlight;
	private int passengerCounter;
	private boolean active;
	@ManyToOne(optional = false)
	private Flight flight;
	
	@OneToMany(mappedBy = "flightInstance")
	private Set<ReservationLine> reservationLine;
	
	@Version
	private int version;
	
	public FlightInstance() {}
	
	public FlightInstance(ZonedDateTime departureDate, ZonedDateTime arrivalDate, String statusFlight,
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

	
	public ZonedDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(ZonedDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public ZonedDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(ZonedDateTime arrivalDate) {
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
	
	public FlightInstanceDTO toDto() {
		return new FlightInstanceDTO(this.id, 
									 ZonedDateUtils.parseString(this.arrivalDate), 
									 ZonedDateUtils.parseString(this.departureDate), 
									 statusFlight, passengerCounter, this.getFlight().getId(), active);
	}
}