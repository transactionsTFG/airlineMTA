package business.aircraft;

public class AircraftDTO {
	private long id;
	private int capacity;
	private String model;
	private boolean active;
	
	public AircraftDTO() {}
	
	public AircraftDTO(long id, int capacity, String model, boolean active) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.model = model;
		this.active = active;
	}
	
	public AircraftDTO(long id, int capacity, String model) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.model = model;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	 
}
