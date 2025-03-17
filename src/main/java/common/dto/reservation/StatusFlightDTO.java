package common.dto.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusFlightDTO {
    private String status;
    private LocalTime timeReturnDate;
    private LocalDate returnDate;
    private int seats;
    private double price;
}
