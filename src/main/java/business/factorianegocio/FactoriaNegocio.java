package business.factorianegocio;

import business.flight.SAAFlight;
import business.reservation.SAAReservation;

public abstract class FactoriaNegocio {
    private static FactoriaNegocio instancia;
    
    protected FactoriaNegocio(){}

    public static synchronized FactoriaNegocio getInstancia() {
		if (instancia == null) 
            instancia = new FactoriaNegocioImpl();
		return instancia;
	}

    public abstract SAAFlight generateServicesFlight();
    public abstract SAAReservation generateServicesReservation();

}
