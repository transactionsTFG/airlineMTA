package common.dto.soap.response;

import business.flight.FlightDTO;

import java.time.LocalTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FlightSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightSOAP {
    @XmlElement
    private long id;
    @XmlElement
    private String codeFlight;
    @XmlElement
    private String weekDay;
    @XmlElement
    private LocalTime arrivalTime;
    @XmlElement
    private LocalTime departureTime;
    @XmlElement
    private long idAircraft;
    @XmlElement
    private long idOriginFlight;
    @XmlElement
    private long idDestinationFlight;
    @XmlElement
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

    public static FlightSOAP toSOAP(FlightDTO flightDTO){
        FlightSOAP flightSOAP = new FlightSOAP();
        flightSOAP.setId(flightDTO.getId());
        flightSOAP.setCodeFlight(flightDTO.getCodeFlight());
        flightSOAP.setWeekDay(flightDTO.getWeekDay());
        flightSOAP.setArrivalTime(flightDTO.getArrivalTime());
        flightSOAP.setDepartureTime(flightDTO.getDepartureTime());
        flightSOAP.setIdAircraft(flightDTO.getIdAircraft());
        flightSOAP.setIdOriginFlight(flightDTO.getIdOriginFlight());
        flightSOAP.setIdDestinationFlight(flightDTO.getIdDestinationFlight());
        flightSOAP.setActive(flightDTO.isActive());
        return flightSOAP;
    }

}
