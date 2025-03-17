package business.flight;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

import business.aircraft.Aircraft;
import business.airport.Airport;
import business.flightinstance.FlightInstance;
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
	@NamedQuery(name = "business.flight.Flight.getAll",
				query = "SELECT f FROM Flight f")

})
public class Flight implements Serializable {
	private static final long serialVersionUID = 1486204349182112560L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String codeFlight;
	@Column(nullable = false)
	private String weekDay;
	@Column(nullable = false)
	private LocalTime arrivalTime;
	@Column(nullable = false)
	private LocalTime departureTime;
	private boolean active;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Aircraft aircraft;
	@OneToMany(mappedBy = "flight")
	private Set<FlightInstance> flightInstance;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Airport origin;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Airport destination;
	@Version
	private int version;
	
	public Flight() {}
	
	public Flight(String codeFlight, String weekDay, LocalTime arrivalTime, LocalTime departureTime, boolean active,
			Aircraft aircraft, Airport origin, Airport destination) {
		super();
		this.codeFlight = codeFlight;
		this.weekDay = weekDay;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.active = active;
		this.aircraft = aircraft;
		this.origin = origin;
		this.destination = destination;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeFlight() {
		return codeFlight;
	}

	public void setCodeFlight(String codeFlight) {
		this.codeFlight = codeFlight;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Set<FlightInstance> getFlightInstance() {
		return flightInstance;
	}

	public void setFlightInstance(Set<FlightInstance> flightInstance) {
		this.flightInstance = flightInstance;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
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
	
	
	public FlightDTO toDto() {
		FlightDTO f = new FlightDTO();
		f.setId(this.id);
		f.setCodeFlight(this.codeFlight);
		f.setWeekDay(this.weekDay);
		f.setArrivalTime(this.arrivalTime);
		f.setDepartureTime(this.departureTime);
		f.setIdAircraft(this.getAircraft().getId());
		f.setIdOriginFlight(this.getOrigin().getId());
		f.setIdDestinationFlight(this.getDestination().getId());
		return f;
	}
}