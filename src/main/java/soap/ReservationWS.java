package soap;

import business.factorianegocio.FactoriaNegocio;
import business.reservation.ReservationDTO;
import business.reservation.SAAReservation;
import common.consts.WebMethodConsts;
import common.dto.result.Result;
import common.dto.soap.request.MakeReservationRequestSOAP;
import common.dto.soap.request.ModifyReservationRequestSOAP;
import common.dto.soap.response.ReservationSOAP;
import common.dto.soap.response.SoapResponse;
import common.mapper.SoapResponseMapper;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "ReservationWS")
public class ReservationWS {
    private final SAAReservation servicesReservation;

    public ReservationWS(){
        this.servicesReservation = FactoriaNegocio.getInstancia().generateServicesReservation();
    }

    @WebMethod(operationName=WebMethodConsts.OP_MAKE_RESERVATION)
    public SoapResponse<ReservationSOAP> make(@WebParam(name="request") MakeReservationRequestSOAP r){
        final Result<ReservationDTO> result = this.servicesReservation.make(r.getCustomer(), r.getReservation(), r.getIdFlight(), r.getNumberOfSeats());
        return SoapResponseMapper
                .toSoapResponse(result.getMessage(), 
                                ReservationSOAP.toSOAP(result.getData()), 
                                result.isSuccess());
    }

    @WebMethod(operationName=WebMethodConsts.OP_CANCEL_RESERVATION)
    public SoapResponse<Void> cancel(@WebParam(name = "idFlight") final long idFlight, @WebParam(name = "idFlightInstance") final long idFlightInstance){
        final Result<Void> result = this.servicesReservation.cancel(idFlight, idFlightInstance);
        return SoapResponseMapper.toSoapResponse(result);
    }

    @WebMethod(operationName=WebMethodConsts.OP_MODIFY_RESERVATION)
    public SoapResponse<ReservationSOAP> modify(@WebParam(name="request") ModifyReservationRequestSOAP m){
        final Result<ReservationDTO> result = this.servicesReservation.modify(m.getReservation(), m.getReservationLine());
        return SoapResponseMapper
                .toSoapResponse(result.getMessage(), 
                                ReservationSOAP.toSOAP(result.getData()), 
                                result.isSuccess());
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_RESERVATION)
    public SoapResponse<ReservationSOAP> searchReservation(@WebParam(name = "idReservation") final long idReservation){
        final Result<ReservationDTO> result = this.servicesReservation.read(idReservation);
        return SoapResponseMapper
                .toSoapResponse(result.getMessage(), 
                        ReservationSOAP.toSOAP(result.getData()), 
                        result.isSuccess());
    }

}
