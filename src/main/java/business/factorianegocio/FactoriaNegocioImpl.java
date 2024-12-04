package business.factorianegocio;

import business.flight.SAAFlight;
import business.flight.SAAFlightImpl;
import business.reservation.SAAReservation;
import business.reservation.SAAReservationImpl;

public class FactoriaNegocioImpl extends FactoriaNegocio {

	protected FactoriaNegocioImpl(){}

	@Override
	public SAAFlight generateServicesFlight() {
		return new SAAFlightImpl();
	}

	@Override
	public SAAReservation generateServicesReservation() {
		return new SAAReservationImpl();
	}
}
