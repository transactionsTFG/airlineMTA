package business.reservation;

import java.util.Map;

import business.customer.CustomerDTO;
import common.dto.reservation.UpdateReservationDTO;
import common.dto.result.Result;

public interface SAAReservation {
	Result<ReservationDTO> make(final CustomerDTO customer, final Map<Long, Integer> listIdFlightInstance);
	Result<UpdateReservationDTO> modify(final long idReservation, final long idCustomer, final Map<Long, Integer> idFlightInstanceWithSeatsMap);
	Result<Double> cancel(final long idReservation);
	Result<ReservationDTO> read(final long idReservation);
}
