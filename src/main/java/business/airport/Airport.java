package business.airport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import jakarta.persistence.Id;
import java.util.Set;

import business.flight.Flight;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

@Entity
public class Airport implements Serializable {
	private static final long serialVersionUID = 8703515795227728444L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private String codeName;
	@OneToMany(mappedBy = "origin")
	private Set<Flight> flightOrigin;
	@OneToMany(mappedBy = "destination")
	private Set<Flight> flightDestination;
	private boolean active;
	@Version
	private int version;
	
	public Airport() {}
		
	public Airport(String name, String city, String country, String codeName, boolean active) {
		super();
		this.name = name;
		this.city = city;
		this.country = country;
		this.codeName = codeName;
		this.active = active;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Set<Flight> getFlightOrigin() {
		return flightOrigin;
	}
	public void setFlightOrigin(Set<Flight> flightOrigin) {
		this.flightOrigin = flightOrigin;
	}
	public Set<Flight> getFlightDestination() {
		return flightDestination;
	}
	public void setFlightDestination(Set<Flight> flightDestination) {
		this.flightDestination = flightDestination;
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
}