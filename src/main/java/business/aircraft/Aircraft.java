package business.aircraft;


import java.io.Serializable;
import java.util.Set;

import business.flight.Flight;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
@NamedQueries({
	@NamedQuery(name = "business.aircraft.Aircraft.getAll",
				query = "SELECT a FROM Aircraft a")

})
public class Aircraft implements Serializable {
	private static final long serialVersionUID = -8829206780116114763L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int capacity;
	@Column(nullable = false)
	private String model;
	@OneToMany(mappedBy = "aircraft")
	private Set<Flight> flight;
	private boolean active;

	@Version
	private int version;
	
	public Aircraft() {}
	

	public Aircraft(int capacity, String model, boolean active) {
		super();
		this.capacity = capacity;
		this.model = model;
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Set<Flight> getFlight() {
		return flight;
	}

	public void setFlight(Set<Flight> flight) {
		this.flight = flight;
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
	
	public AircraftDTO toDto() {
		return new AircraftDTO(id, capacity, model, active);
	}
	
}
