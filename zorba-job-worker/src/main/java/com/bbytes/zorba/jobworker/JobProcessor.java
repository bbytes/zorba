/**
 * 
 */
package com.bbytes.zorba.jobworker;

import java.util.Map;

import com.bbytes.zorba.jobworker.exception.ProcessingException;

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

public interface JobProcessor {


	/**
	 * Process the job given the jobName and jobData
	 * @param jobName
	 * @param jobData
	 * @throws ProcessingException
	 */
	void processJob(String jobName, Map<String, ?> jobData) throws ProcessingException;
}
