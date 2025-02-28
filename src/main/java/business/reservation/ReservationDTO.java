package business.reservation;

import lombok.Data;

@Data
public class ReservationDTO {
	private long id;
	private String date;
	private double total;
	private long idCustomer;
	private boolean active;
}
