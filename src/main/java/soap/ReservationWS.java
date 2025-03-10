package soap;

import business.reservation.ReservationDTO;
import business.reservation.SAAReservation;
import common.consts.WebMethodConsts;
import common.dto.reservation.NewReservationDTO;
import common.dto.reservation.UpdateReservationDTO;
import common.dto.result.Result;
import common.dto.soap.request.MakeReservationRequestSOAP;
import common.dto.soap.request.ModifyReservationRequestSOAP;
import common.dto.soap.response.NewReservationSOAP;
import common.dto.soap.response.ReservationSOAP;
import common.dto.soap.response.SoapResponse;
import common.dto.soap.response.UpdateReservationSOAP;
import common.mapper.ReservationMapper;
import common.mapper.SoapResponseMapper;


import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import weblogic.wsee.wstx.wsat.Transactional;

@WebService(serviceName = "ReservationWS")
public class ReservationWS {

    private final SAAReservation servicesReservation;

    @Inject
    public ReservationWS(final SAAReservation servicesReservation){
        this.servicesReservation = servicesReservation;
    }

    @WebMethod(operationName=WebMethodConsts.OP_MAKE_RESERVATION)
    @Transactional(version = Transactional.Version.WSAT12, value = Transactional.TransactionFlowType.MANDATORY)
    public SoapResponse<NewReservationSOAP> make(@WebParam(name="request") MakeReservationRequestSOAP r){
        final Result<NewReservationDTO> result = this.servicesReservation.make(r.getCustomer(), r.toFlightSeatsMap());
        return SoapResponseMapper.toSoapResponse(result.getMessage(), new NewReservationSOAP().toSOAP(result.getData()), result.isSuccess());
    }

    @WebMethod(operationName=WebMethodConsts.OP_CANCEL_RESERVATION)
    @Transactional(version = Transactional.Version.WSAT12, value = Transactional.TransactionFlowType.MANDATORY)
    public SoapResponse<Double> cancel(@WebParam(name = "idReservation") final long idReservation){
        final Result<Double> result = this.servicesReservation.cancel(idReservation);
        return SoapResponseMapper.toSoapResponse(result);
    }

    @WebMethod(operationName=WebMethodConsts.OP_MODIFY_RESERVATION)
    @Transactional(version = Transactional.Version.WSAT12, value = Transactional.TransactionFlowType.MANDATORY)
    public SoapResponse<UpdateReservationSOAP> modify(@WebParam(name="request") ModifyReservationRequestSOAP m){
        final Result<UpdateReservationDTO> result = this.servicesReservation.modify(m.getIdReservation(), m.toFlightSeatsMap());
        return SoapResponseMapper.toSoapResponse(result.getMessage(), ReservationMapper.INSTANCE.toUpdateReservationSOAP(result.getData()), result.isSuccess());
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_RESERVATION)
    @Transactional(version = Transactional.Version.WSAT12, value = Transactional.TransactionFlowType.MANDATORY)
    public SoapResponse<ReservationSOAP> searchReservation(@WebParam(name = "idReservation") final long idReservation){
        final Result<ReservationDTO> result = this.servicesReservation.read(idReservation);
        return SoapResponseMapper.toSoapResponse(result.getMessage(), ReservationSOAP.toSOAP(result.getData()), result.isSuccess());           
    }

}
