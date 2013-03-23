/**
 * 
 */
package com.bbytes.zorba.jobworker;

import java.util.Map;

import com.bbytes.zorba.domain.IJob;
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
	
	/**
	 * Returns the map of job name against a {@link IJob} class
	 * @return
	 * @throws ProcessingException
	 */
	Map<String,Class<IJob>> getJobMap() throws ProcessingException;
}
