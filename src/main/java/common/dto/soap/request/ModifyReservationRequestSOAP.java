package common.dto.soap.request;

import business.reservation.ReservationDTO;
import business.reservationline.ReservationLineDTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

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
