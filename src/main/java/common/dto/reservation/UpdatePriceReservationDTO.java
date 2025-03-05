package common.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePriceReservationDTO {
    private long idFlightInstance;
    private double price;
}
