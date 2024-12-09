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
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "ReservationWS")
public class ReservationWS {
    @EJB
    private SAAReservation servicesReservation;

    public ReservationWS(){
        //this.servicesReservation = FactoriaNegocio.getInstancia().generateServicesReservation();
    }

    @WebMethod(operationName=WebMethodConsts.OP_MAKE_RESERVATION)
    public SoapResponse<ReservationSOAP> make(@WebParam(name="request") MakeReservationRequestSOAP r){
        final Result<ReservationDTO> result = this.servicesReservation.make(r.getCustomer(), r.getReservation(), r.getIdFlight(), r.getNumberOfSeats());
        final ReservationSOAP data = result.isSuccess() ? ReservationSOAP.toSOAP(result.getData()) : null;
        return SoapResponseMapper
                .toSoapResponse(result.getMessage(), 
                                data, 
                                result.isSuccess());
    }

    @WebMethod(operationName=WebMethodConsts.OP_CANCEL_RESERVATION)
    public SoapResponse<Void> cancel(@WebParam(name = "idReservation") final long idReservation, @WebParam(name = "idFlightInstance") final long idFlightInstance){
        final Result<Void> result = this.servicesReservation.cancel(idReservation, idFlightInstance);
        return SoapResponseMapper.toSoapResponse(result);
    }

    @WebMethod(operationName=WebMethodConsts.OP_MODIFY_RESERVATION)
    public SoapResponse<ReservationSOAP> modify(@WebParam(name="request") ModifyReservationRequestSOAP m){
        final Result<ReservationDTO> result = this.servicesReservation.modify(m.getReservation(), m.getReservationLine());
        final ReservationSOAP data = result.isSuccess() ? ReservationSOAP.toSOAP(result.getData()) : null;
        return SoapResponseMapper
                .toSoapResponse(result.getMessage(), 
                                data, 
                                result.isSuccess());
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_RESERVATION)
    public SoapResponse<ReservationSOAP> searchReservation(@WebParam(name = "idReservation") final long idReservation){
        final Result<ReservationDTO> result = this.servicesReservation.read(idReservation);
        final ReservationSOAP data = result.isSuccess() ? ReservationSOAP.toSOAP(result.getData()) : null;
        return SoapResponseMapper
                .toSoapResponse(result.getMessage(), 
                        data, 
                        result.isSuccess());
    }

}
