/**
 * 
 */
package com.bbytes.zorba.jobworker;

import com.bbytes.zorba.jobworker.exception.JobProcessingException;

/**
 * Interface to process the jobs picked from the queues.
 * 
 * TODO : Expand this interface 
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 * @since 0.0.1
 *
 */

public interface IJobProcessor {

	/**
	 * 
	 * 
	 * @throws JobProcessingException
	 */
	void processJobs() throws JobProcessingException;
}
