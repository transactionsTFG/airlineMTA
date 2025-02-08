package common.exception;

import javax.xml.soap.SOAPConstants;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPException;

public class SAException extends SOAPFaultException {
	private static final long serialVersionUID = 5067456836242850281L;
	
	public SAException(final String msg){
		super(createFault(msg));
	}

	private static SOAPFault createFault(final String msg){
		try {
			SOAPFactory factory = SOAPFactory.newInstance();
			SOAPFault fault = factory.createFault();
			fault.setFaultCode(SOAPConstants.SOAP_SENDER_FAULT);
			fault.setFaultString(msg);
			fault.setFaultActor("airlineMTA");
			return fault;
		} catch (SOAPException e) {
			throw new RuntimeException("Error creando SOAPFaultException", e);
		}
	}
}
