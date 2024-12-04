package business.airport;

public class AirportDTO {
	private long id;
    private String name;
    private String city;
    private String country;
    private String codeName;
	private boolean active;

    public AirportDTO() {}

    public AirportDTO(long id, String name, String city, String country, String codeName) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.codeName = codeName;
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
    
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
