package common.dto.soap.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import common.dto.reservation.StatusFlightDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class StatusFlightSOAP {
    @XmlElement
    private String status;
    @XmlElement
    private String returnDate;
    @XmlElement
    private int seats;
    @XmlElement
    private double price;
    public StatusFlightSOAP(StatusFlightDTO s){
        this.status = s.getStatus();
        this.returnDate = s.getReturnDate();
        this.seats = s.getSeats();
        this.price = s.getPrice();
    }
}
