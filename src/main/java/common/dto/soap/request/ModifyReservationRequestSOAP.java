package common.dto.soap.request;

import business.reservation.ReservationDTO;
import business.reservationline.ReservationLineDTO;
import common.dto.flight.IdFlightInstanceWithSeatsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ModifyReservationRequestSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyReservationRequestSOAP {
    @XmlElement
    private long idReservation;
    @XmlElement
    private long idCustomer;
    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
    private List<IdFlightInstanceWithSeatsDTO> idFlightInstanceWithSeats;

    public Map<Long, Integer> toFlightSeatsMap() {
        return this.idFlightInstanceWithSeats.stream()
            .collect(Collectors.toMap(IdFlightInstanceWithSeatsDTO::getIdFlightInstance, IdFlightInstanceWithSeatsDTO::getSeats));
    }

}
