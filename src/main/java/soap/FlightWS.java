package soap;

import business.consts.WebMethodConsts;
import business.factorianegocio.FactoriaNegocio;
import business.flight.FlightDTO;
import business.flight.SAAFlight;
import business.result.Result;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(serviceName = "FlightWS")
public class FlightWS {
    private final SAAFlight servicesFlight;

    public FlightWS(){
        this.servicesFlight = FactoriaNegocio.getInstancia().generateServicesFlight();
    }

    @WebMethod(operationName=WebMethodConsts.OP_SEARCH_FLIGHT)
    public Result<FlightDTO> search(final long idFlight){
        return this.servicesFlight.search(idFlight);
    }

}
