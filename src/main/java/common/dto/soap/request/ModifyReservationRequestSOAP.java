package common.dto.soap.request;

import business.reservation.ReservationDTO;
import business.reservationline.ReservationLineDTO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ModifyReservationRequestSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModifyReservationRequestSOAP {
    @XmlElement
    private ReservationDTO reservation;
    @XmlElement
    private ReservationLineDTO reservationLine;
    public ModifyReservationRequestSOAP() {}

    public ModifyReservationRequestSOAP(ReservationDTO reservation, ReservationLineDTO reservationLine) {
        this.reservation = reservation;
        this.reservationLine = reservationLine;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public ReservationLineDTO getReservationLine() {
        return reservationLine;
    }

    public void setReservationLine(ReservationLineDTO reservationLine) {
        this.reservationLine = reservationLine;
    }
}
