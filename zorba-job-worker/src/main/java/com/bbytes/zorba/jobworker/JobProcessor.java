/**
 * 
 */
package com.bbytes.zorba.jobworker;

import java.io.Serializable;
import java.util.Map;

import com.bbytes.zorba.domain.IJob;
import com.bbytes.zorba.jobworker.domain.JobExecutionContext;
import com.bbytes.zorba.jobworker.domain.ZorbaData;
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
	 * 
	 * @param jobExecutionId 
	 * @param jobName
	 * @param jobData
	 * @param executionContext 
	 * @throws ProcessingException
	 */
	void processJob(String jobExecutionId, String jobName, ZorbaData<String, Serializable> jobData, JobExecutionContext executionContext) throws ProcessingException;
	
	/**
	 * Returns the map of job name against a {@link IJob} class
	 * @return
	 * @throws ProcessingException
	 */
	Map<String,Class<IJob>> getJobMap() throws ProcessingException;
	
	
	/**
	 * Sets the job Map for the processor
	 * @param jobMap
	 * @throws ProcessingException
	 */
	void setJobMap(Map<String,Class<IJob>>  jobMap) throws ProcessingException;
	
}
