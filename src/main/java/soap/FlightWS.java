package soap;

import business.flight.FlightDTO;
import business.flight.SAAFlight;
import common.consts.WebMethodConsts;
import common.dto.result.Result;
import common.dto.soap.response.FlightSOAP;
import common.dto.soap.response.SoapResponse;
import common.mapper.SoapResponseMapper;
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "FlightWS")
public class FlightWS {
    
    @EJB
    private SAAFlight servicesFlight;

    public FlightWS(){
        //this.servicesFlight = FactoriaNegocio.getInstancia().generateServicesFlight();
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_FLIGHT)
    public SoapResponse<FlightSOAP> search(@WebParam(name = "idFlightSearch") final long idFlight){
        final Result<FlightDTO> f = this.servicesFlight.search(idFlight);
        final FlightSOAP data = f.isSuccess() ? FlightSOAP.toSOAP(f.getData()) : null;
        return SoapResponseMapper
                .toSoapResponse(f.getMessage(), 
                                data, 
                                f.isSuccess());
    }
}
