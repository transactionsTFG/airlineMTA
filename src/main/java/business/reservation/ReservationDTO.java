package business.reservation;

public class ReservationDTO {
	private long id;
	private String date;
	private double total;
	private long idCustomer;
	private boolean active;

	public ReservationDTO() {}
	
	

	public ReservationDTO(String date, double total, boolean active) {
		super();
		this.date = date;
		this.total = total;
		this.active = active;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
