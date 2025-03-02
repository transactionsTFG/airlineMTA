package business.airport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Set;

import business.country.Country;
import business.flight.Flight;

import javax.persistence.OneToMany;
import javax.persistence.Version;

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
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Country country;
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
		
	public Airport(String name, String city, Country country, String codeName, boolean active) {
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
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
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