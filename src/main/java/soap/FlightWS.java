package soap;

import business.flight.FlightDTO;
import business.flight.SAAFlight;
import common.consts.WebMethodConsts;
import common.dto.flight.FlightData;
import common.dto.result.Result;
import common.dto.soap.request.ParamFlightSOAP;
import common.dto.soap.response.FlightDataListSOAP;
import common.dto.soap.response.FlightSOAP;
import common.dto.soap.response.SoapResponse;
import common.mapper.SoapResponseMapper;
import weblogic.wsee.wstx.wsat.Transactional;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "FlightWS")
public class FlightWS {

    private final SAAFlight servicesFlight;
    
    @Inject //Inyeccion por constructora para los test unitarios
    public FlightWS(final SAAFlight servicesFlight){
        this.servicesFlight = servicesFlight;
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_FLIGHT)
    public SoapResponse<FlightSOAP> search(@WebParam(name = "idFlightSearch") final long idFlight){
        final Result<FlightDTO> f = this.servicesFlight.search(idFlight);
        return SoapResponseMapper.toSoapResponse(f.getMessage(), FlightSOAP.toSOAP(f.getData()), f.isSuccess());
    }
    
    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_FLIGHTS)
    public List<FlightDataListSOAP> searchFlight(@WebParam(name = "paramSearch") final ParamFlightSOAP param){
        List<FlightData> flights =  this.servicesFlight
                    .searchWithParams(param.getCountryOrigin(), 
                                      param.getCountryDestination(), 
                                      param.getCityOrigin(), 
                                      param.getCityDestination(), 
                                      param.getDateOrigin());
        return  flights.stream().map(f -> new FlightDataListSOAP().toSOAP(f)).toList();  
    }
}
