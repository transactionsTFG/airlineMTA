package soap;

import business.consts.WebMethodConsts;
import business.customer.CustomerDTO;
import business.factorianegocio.FactoriaNegocio;
import business.reservation.ReservationDTO;
import business.reservation.SAAReservation;
import business.reservationline.ReservationLineDTO;
import business.result.Result;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(serviceName = "ReservationWS")
public class ReservationWS {
    private final SAAReservation servicesReservation;

    public ReservationWS(){
        this.servicesReservation = FactoriaNegocio.getInstancia().generateServicesReservation();
    }

    @WebMethod(operationName=WebMethodConsts.OP_MAKE_RESERVATION)
    public Result<ReservationDTO> make(final CustomerDTO c, final ReservationDTO r, final long idFlight, final int numberOfSeats){
        return this.servicesReservation.make(c, r, idFlight, numberOfSeats);
    }

    @WebMethod(operationName=WebMethodConsts.OP_CANCEL_RESERVATION)
    public Result<Void> cancel(final long idFlight, final long idFlightInstance){
        return this.servicesReservation.cancel(idFlight, idFlightInstance);
    }

    @WebMethod(operationName=WebMethodConsts.OP_MODIFY_RESERVATION)
    public Result<ReservationDTO> modify(final ReservationDTO r, final ReservationLineDTO rL){
        return this.servicesReservation.modify(r, rL);
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_RESERVATION)
    public Result<ReservationDTO> search(final long idReservation){
        return this.servicesReservation.read(idReservation);
    }

}
