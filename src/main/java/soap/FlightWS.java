package soap;

import business.flight.FlightDTO;
import business.flight.SAAFlight;
import common.consts.WebMethodConsts;
import common.dto.result.Result;
import common.dto.soap.response.FlightSOAP;
import common.dto.soap.response.SoapResponse;
import common.mapper.SoapResponseMapper;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "FlightWS")
public class FlightWS {

    private final SAAFlight servicesFlight;
    
    @Inject //Inyeccion por constructora para los test unitarios
    public FlightWS(final SAAFlight servicesFlight){
        this.servicesFlight = servicesFlight;
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_FLIGHT)
    public SoapResponse<FlightSOAP> search(@WebParam(name = "idFlightSearch") final long idFlight){
        try {
            final Result<FlightDTO> f = this.servicesFlight.search(idFlight);
            return SoapResponseMapper.toSoapResponse(f.getMessage(), FlightSOAP.toSOAP(f.getData()), f.isSuccess());
        } catch(Exception e){
            System.out.println(e.getMessage());
            return SoapResponseMapper.toSoapResponse(e.getMessage(), null, false);
        }
    }
}
