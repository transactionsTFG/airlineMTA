package common.dto.soap.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlRootElement(name = "ParamFlightSOAP")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParamFlightSOAP {
    @XmlElement
    private String countryOrigin;
    @XmlElement
    private String countryDestination;
    @XmlElement
    private String cityOrigin;  
    @XmlElement
    private String cityDestination;
    @XmlElement
    private String dateOrigin;
}
