package common.dto.soap.request;

import business.customer.CustomerDTO;
import business.reservation.ReservationDTO;
import common.dto.flight.IdFlightInstanceWithSeatsDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MakeReservationRequestSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class MakeReservationRequestSOAP {
    @XmlElement
    private CustomerDTO customer;

    @XmlElement
    private ReservationDTO reservation;

    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
    private List<IdFlightInstanceWithSeatsDTO> idsFlightWithSeats;

    public MakeReservationRequestSOAP() {}

    public MakeReservationRequestSOAP(CustomerDTO customer, ReservationDTO reservation, List<IdFlightInstanceWithSeatsDTO> listIdFlight) {
        this.customer = customer;
        this.reservation = reservation;
        this.idsFlightWithSeats = listIdFlight;
    
    }

    public Map<Long, Integer> toFlightSeatsMap() {
        return idsFlightWithSeats.stream()
            .collect(Collectors.toMap(IdFlightInstanceWithSeatsDTO::getIdFlightInstance, IdFlightInstanceWithSeatsDTO::getSeats));
    }

}
