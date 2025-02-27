package business.reservation;

import java.util.Map;

import business.customer.CustomerDTO;
import business.reservationline.ReservationLineDTO;
import common.dto.result.Result;

public interface SAAReservation {
	Result<ReservationDTO> make(final CustomerDTO customer, final ReservationDTO reservationDto, final Map<Long, Integer> listIdFlightInstance);
	Result<ReservationDTO> modify(final ReservationDTO reservation, final ReservationLineDTO reservationLine);
	Result<Void> cancel(final long idReservation);
	Result<ReservationDTO> read(final long idReservation);
}
