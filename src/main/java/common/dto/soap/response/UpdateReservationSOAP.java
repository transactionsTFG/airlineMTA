package common.dto.soap.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "UpdateReservationSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateReservationSOAP {
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
    @XmlElement
    private double updatePrice;
    @XmlElement
    private List<UpdatePriceReservationSOAP> updatePriceReservationSOAP;
}
