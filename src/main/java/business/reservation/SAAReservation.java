package business.reservation;

import business.customer.CustomerDTO;
import business.reservationline.ReservationLineDTO;
import common.dto.result.Result;
import common.exception.SAException;

public interface SAAReservation {
	Result<ReservationDTO> make(final CustomerDTO customer, final ReservationDTO reservationDto, final long idFlightInstance, final int numberOfSeats) throws SAException;
	Result<ReservationDTO> modify(final ReservationDTO reservation, final ReservationLineDTO reservationLine) throws SAException;
	Result<Void> cancel(final long idReservation, final long idFlightInstance) throws SAException;
	Result<ReservationDTO> read(final long idReservation) throws SAException;
}
