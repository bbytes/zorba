/**
 * 
 */
package com.bbytes.zorba.messaging.exception;

/**
 * Exception class for all messaging related issues
 * 
 * @author Dhanush Gopinath
 *
 */
public class MessagingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6557589243015313236L;

	/**
	 * 
	 */
	public MessagingException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MessagingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MessagingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MessagingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
