package soap;

import business.reservation.ReservationDTO;
import business.reservation.SAAReservation;
import common.consts.WebMethodConsts;
import common.dto.result.Result;
import common.dto.soap.request.MakeReservationRequestSOAP;
import common.dto.soap.request.ModifyReservationRequestSOAP;
import common.dto.soap.response.ReservationSOAP;
import common.dto.soap.response.SoapResponse;
import common.mapper.SoapResponseMapper;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "ReservationWS")
public class ReservationWS {

    private final SAAReservation servicesReservation;

    @Inject
    public ReservationWS(final SAAReservation servicesReservation){
        this.servicesReservation = servicesReservation;
    }

    @WebMethod(operationName=WebMethodConsts.OP_MAKE_RESERVATION)
    public SoapResponse<ReservationSOAP> make(@WebParam(name="request") MakeReservationRequestSOAP r){
        try {
            final Result<ReservationDTO> result = this.servicesReservation.make(r.getCustomer(), r.getReservation(), r.getIdFlight(), r.getNumberOfSeats());
            return SoapResponseMapper.toSoapResponse(result.getMessage(), ReservationSOAP.toSOAP(result.getData()), result.isSuccess());
        } catch (Exception e) {
            return SoapResponseMapper.toSoapResponse(e.getMessage(), null, false);
        }
    }

    @WebMethod(operationName=WebMethodConsts.OP_CANCEL_RESERVATION)
    public SoapResponse<Void> cancel(@WebParam(name = "idReservation") final long idReservation, @WebParam(name = "idFlightInstance") final long idFlightInstance){
        try {
            final Result<Void> result = this.servicesReservation.cancel(idReservation, idFlightInstance);
            return SoapResponseMapper.toSoapResponse(result);
        } catch(Exception e){
            return SoapResponseMapper.toSoapResponse(e.getMessage(), null, false);
        }
    }

    @WebMethod(operationName=WebMethodConsts.OP_MODIFY_RESERVATION)
    public SoapResponse<ReservationSOAP> modify(@WebParam(name="request") ModifyReservationRequestSOAP m){
        try {
            final Result<ReservationDTO> result = this.servicesReservation.modify(m.getReservation(), m.getReservationLine());
            return SoapResponseMapper.toSoapResponse(result.getMessage(), ReservationSOAP.toSOAP(result.getData()), result.isSuccess());
        } catch(Exception e){
            return  SoapResponseMapper.toSoapResponse(e.getMessage(), null, false);
        }
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_RESERVATION)
    public SoapResponse<ReservationSOAP> searchReservation(@WebParam(name = "idReservation") final long idReservation){
        try {
            final Result<ReservationDTO> result = this.servicesReservation.read(idReservation);
            return SoapResponseMapper.toSoapResponse(result.getMessage(), ReservationSOAP.toSOAP(result.getData()), result.isSuccess());
        } catch(Exception e){
            return  SoapResponseMapper.toSoapResponse(e.getMessage(), null, false);
        }               
    }

}
