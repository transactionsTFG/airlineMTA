package business.flight;

public class FlightDTO {
	private long id;
    private String codeFlight;
    private String weekDay;
    private String arrivalTime;
    private String departureTime;
    private long idAircraft;
    private long idOriginFlight;
    private long idDestinationFlight;
    private boolean active;
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
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public long getIdAircraft() {
		return idAircraft;
	}
	public void setIdAircraft(long idAircraft) {
		this.idAircraft = idAircraft;
	}
	public long getIdOriginFlight() {
		return idOriginFlight;
	}
	public void setIdOriginFlight(long idOriginFlight) {
		this.idOriginFlight = idOriginFlight;
	}
	public long getIdDestinationFlight() {
		return idDestinationFlight;
	}
	public void setIdDestinationFlight(long idDestinationFlight) {
		this.idDestinationFlight = idDestinationFlight;
	}
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
