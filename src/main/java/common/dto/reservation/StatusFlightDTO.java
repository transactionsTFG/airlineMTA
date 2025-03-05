package common.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusFlightDTO {
    private String status;
    private String returnDate;
    private int seats;
}
