package common.dto.reservation;

import java.util.List;

import lombok.Data;

@Data
public class NewReservationDTO {
    private long id;
	private String date;
	private double total;
	private boolean active;
	private List<StatusFlightDTO> statusFlightDTO;

	public NewReservationDTO(long id, String date, double total, final List<StatusFlightDTO> statusFlightDTO) {
		this.id = id;
		this.date = date;
		this.total = total;
		this.active = true;
		this.statusFlightDTO = statusFlightDTO;
	}
}
