package common.dto.soap.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "SoapResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({FlightSOAP.class, ReservationSOAP.class})
public class SoapResponse<T> {
    @XmlElement
    private boolean success;
    @XmlElement
	private T data;
    @XmlElement
    private String message;

    public SoapResponse(){}

    public SoapResponse(boolean success, T data, String msg) {
        this.success = success;
        this.data = data;
        this.message = msg;
    }

    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }



}
