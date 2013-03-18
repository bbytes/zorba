/**
 * 
 */
package com.bbytes.zorba.exception;

/**
 * Exception class for Job Execution issues
 * 
 * @author Dhanush Gopinath
 * 
 *
 */
public class JobExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149422749509043906L;

	/**
	 * 
	 */
	public JobExecutionException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public JobExecutionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public JobExecutionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JobExecutionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
