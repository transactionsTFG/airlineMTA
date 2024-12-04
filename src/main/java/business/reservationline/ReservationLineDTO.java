package business.reservationline;

public class ReservationLineDTO {
	private long flightInstanceId;
	private long reservationId;
	private int passengerCount;

	public ReservationLineDTO() {}

	public ReservationLineDTO(long flightInstanceId, long reservationId, int passengerCount) {
		super();
		this.flightInstanceId = flightInstanceId;
		this.reservationId = reservationId;
		this.passengerCount = passengerCount;
	}
	
	public long getFlightInstanceId() {
		return flightInstanceId;
	}

	public void setFlightInstanceId(long flightInstanceId) {
		this.flightInstanceId = flightInstanceId;
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	
	  
}
