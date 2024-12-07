package common.dto.soap.response;

import business.reservation.ReservationDTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ReservationSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservationSOAP {
    @XmlElement
    private long id;
    @XmlElement
	private String date;
    @XmlElement
    private double total;
    @XmlElement
	private long idCustomer;
    @XmlElement
    private boolean active;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
    
    public static ReservationSOAP toSOAP(ReservationDTO reservationDTO) {
        ReservationSOAP reservationSOAP = new ReservationSOAP();
        reservationSOAP.setId(reservationDTO.getId());
        reservationSOAP.setDate(reservationDTO.getDate());
        reservationSOAP.setTotal(reservationDTO.getTotal());
        reservationSOAP.setIdCustomer(reservationDTO.getIdCustomer());
        reservationSOAP.setActive(reservationDTO.isActive());
        return reservationSOAP;
    }
}
