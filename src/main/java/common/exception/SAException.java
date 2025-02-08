package common.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SAException extends Exception {
	private static final long serialVersionUID = 5067456836242850281L;
	public SAException(String message) {
		super(message);
	}
}
