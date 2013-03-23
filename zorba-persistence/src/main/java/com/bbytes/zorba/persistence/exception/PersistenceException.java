/**
 * 
 */
package com.bbytes.zorba.persistence.exception;

/**
 * Exception class for all persistence issues
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 *
 */
public class PersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PersistenceException() {
		
	}

	/**
	 * @param message
	 */
	public PersistenceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
