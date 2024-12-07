package common.dto.soap.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

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
