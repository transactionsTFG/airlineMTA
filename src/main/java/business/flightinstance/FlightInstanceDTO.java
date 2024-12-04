package business.flightinstance;

public class FlightInstanceDTO {
	private long id;
    private String departureDate;
    private String arrivalDate;
    private String statusFlight;
    private int passengerCounter;
    private long idFlight;
    private boolean active;
    public FlightInstanceDTO() {}

    
    public FlightInstanceDTO(long id, String departureDate, String arrivalDate, String statusFlight,
			int passengerCounter, long idFlight, boolean active) {
		super();
		this.id = id;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.statusFlight = statusFlight;
		this.passengerCounter = passengerCounter;
		this.idFlight = idFlight;
		this.active = active;
	}



	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
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


	public long getIdFlight() {
		return idFlight;
	}


	public void setIdFlight(long idFlight) {
		this.idFlight = idFlight;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
    
}
