package com.dodoyo.invite.httpservice.excep;

public class ServiceDBException extends ServiceException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6626000333092419342L;

	public ServiceDBException(String message) {
        super(message);
    }

    public ServiceDBException() {
        super();
    }

    public ServiceDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceDBException(Throwable cause) {
        super(cause);
    }
}