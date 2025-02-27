package common.dto.soap.request;

import lombok.Data;

@Data
public class IdFlightInstanceWithSeatsSOAP {
    private long idFlightInstance;
    private int seats;
}
