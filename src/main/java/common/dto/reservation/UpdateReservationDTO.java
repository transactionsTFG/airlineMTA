package common.dto.reservation;

import lombok.Data;

@Data
public class UpdateReservationDTO {
	private long id;
	private String date;
	private double updatePrice;
	private double total;
	private long idCustomer;
	private boolean active;
}

