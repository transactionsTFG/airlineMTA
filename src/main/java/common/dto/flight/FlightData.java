package common.dto.flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FlightData {
    private long id;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String cityDestination;
    private String countryOrigin;
    private String countryDestination;
    private String weekDay;
}
