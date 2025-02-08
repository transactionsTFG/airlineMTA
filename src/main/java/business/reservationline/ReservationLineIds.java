package business.reservationline;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationLineIds {
	private long flightInstanceId;
	private long reservationId;
	public ReservationLineIds() {
		super();
	}
	public ReservationLineIds(long flightInstanceId, long reservationId) {
		super();
		this.flightInstanceId = flightInstanceId;
		this.reservationId = reservationId;
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

}
