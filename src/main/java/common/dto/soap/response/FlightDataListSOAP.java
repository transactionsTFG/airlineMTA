package common.dto.soap.response;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate arrivalDate;
    @XmlElement
    private LocalDate departureDate;
    @XmlElement
    private String cityDestination;
    @XmlElement
    private String countryOrigin;
    @XmlElement
    private String countryDestination;
    @XmlElement
    private String weekDay;
    @XmlElement
    private double price;

    public FlightDataListSOAP toSOAP(final FlightData flightData) {
        this.id = flightData.getId();
        this.arrivalDate = flightData.getArrivalDate();
        this.departureDate = flightData.getDepartureDate();
        this.cityDestination = flightData.getCityDestination();
        this.countryOrigin = flightData.getCountryOrigin();
        this.countryDestination = flightData.getCountryDestination();
        this.weekDay = flightData.getWeekDay();
        this.price = flightData.getPrice();
        return this;
    }
}
