package common.dto.soap.response;

import lombok.Data;

@Data
public class UpdatePriceReservationSOAP {
    private long idFlightInstance;
    private double price;
}
