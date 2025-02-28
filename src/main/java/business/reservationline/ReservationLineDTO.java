package business.reservationline;

public class ReservationLineDTO {
	private long flightInstanceId;
	private int passengerCount;

	public ReservationLineDTO() {}

	public ReservationLineDTO(long flightInstanceId, int passengerCount) {
		super();
		this.flightInstanceId = flightInstanceId;
		this.passengerCount = passengerCount;
	}
	
	public long getFlightInstanceId() {
		return flightInstanceId;
	}

	public void setFlightInstanceId(long flightInstanceId) {
		this.flightInstanceId = flightInstanceId;
	}

	public int getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}

	
	  
}
