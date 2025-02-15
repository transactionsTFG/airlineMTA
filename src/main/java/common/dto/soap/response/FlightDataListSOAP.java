package common.dto.soap.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import common.dto.flight.FlightData;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "FlightSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class FlightDataListSOAP {
    @XmlElement
    private long id;
    @XmlElement
    private String arrivalTime;
    @XmlElement
    private String departureTime;
    @XmlElement
    private String cityDestination;
    @XmlElement
    private String countryOrigin;
    @XmlElement
    private String countryDestination;
    @XmlElement
    private String weekDay;

    public FlightDataListSOAP toSOAP(final FlightData flightData) {
        this.id = flightData.getId();
        this.arrivalTime = flightData.getArrivalTime().toString();
        this.departureTime = flightData.getDepartureTime().toString();
        this.cityDestination = flightData.getCityDestination();
        this.countryOrigin = flightData.getCountryOrigin();
        this.countryDestination = flightData.getCountryDestination();
        this.weekDay = flightData.getWeekDay();
        return this;
    }
}
