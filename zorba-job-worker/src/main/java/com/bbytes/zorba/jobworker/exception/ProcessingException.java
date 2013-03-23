/**
 * 
 */
package com.bbytes.zorba.jobworker.exception;

/**
 * Exception class for JobProcessing issues
 * 
 * @author Dhanush Gopinath
 * 
 *
 */
public class ProcessingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149422749509043906L;

	/**
	 * 
	 */
	public ProcessingException() {
	}

	/**
	 * @param message
	 */
	public ProcessingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ProcessingException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

}
