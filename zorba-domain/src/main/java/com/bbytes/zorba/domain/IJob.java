/**
 * 
 */
package com.bbytes.zorba.domain;

import java.util.Map;

import com.bbytes.zorba.exception.JobExecutionException;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;

/**
 * Interface definition for job to be registered in Zorba
 * 
 * @author Dhanush Gopinath
 * @since 0.0.1
 * @version 0.0.1
 * 
 */
public interface IJob extends IEntity {

	/**
	 * Returns the job name , this name is used to register the job so it should be unique or will
	 * get duplicate job name exception while registering
	 * 
	 * @return
	 */
	public String getJobName();

	/**
	 * The data set on {@link ZorbaRequest} is given to this  method by the framework while
	 * executing the job
	 * 
	 * @param data
	 * @throws JobExecutionException
	 */
	public void execute(Map<String, ?> data) throws JobExecutionException;

}
