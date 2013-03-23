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
public class JobProcessingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149422749509043906L;

	/**
	 * 
	 */
	public JobProcessingException() {
	}

	/**
	 * @param message
	 */
	public JobProcessingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JobProcessingException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JobProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

}
