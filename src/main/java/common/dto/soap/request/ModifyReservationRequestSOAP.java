package common.dto.soap.request;

import business.reservation.ReservationDTO;
import business.reservationline.ReservationLineDTO;
import lombok.Data;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ModifyReservationRequestSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ModifyReservationRequestSOAP {
    @XmlElement
    private long idReservation;
    @XmlElement
    private long idCustomer;
    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
    private Map<Long, Integer> idFlightInstanceWithSeatsMap;
}
