package common.dto.soap.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import common.dto.reservation.NewReservationDTO;

@XmlRootElement(name = "NewReservationSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewReservationSOAP {
    @XmlElement
    private long id;
    @XmlElement
	private String date;
    @XmlElement
    private double total;
    @XmlElement
    private boolean active;
    @XmlElementWrapper(name = "statusFlights")
    @XmlElement(name = "statusFlightSOAP")
    private List<StatusFlightSOAP> statusFlightSOAP;

    public NewReservationSOAP toSOAP(final NewReservationDTO newReservationDTO) {
        this.id = newReservationDTO.getId();
        this.date = newReservationDTO.getDate();
        this.total = newReservationDTO.getTotal();
        this.active = newReservationDTO.isActive();
        this.statusFlightSOAP = newReservationDTO.getStatusFlightDTO().stream().map(StatusFlightSOAP::new).toList();
        return this;
    }
}
