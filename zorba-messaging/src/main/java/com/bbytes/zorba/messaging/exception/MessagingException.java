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
		
	}
	

	/**
	 * @param message
	 */
	public MessagingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MessagingException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MessagingException(String message, Throwable cause) {
		super(message, cause);
	}

}
