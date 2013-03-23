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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ProcessingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ProcessingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ProcessingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
