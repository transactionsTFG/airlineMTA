package common.dto.flight;

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
    private ZonedDateTime arrivalTime;
    private ZonedDateTime departureTime;
    private String cityDestination;
    private String countryOrigin;
    private String countryDestination;
    private String weekDay;
}
