/*
 * Copyright (C) 2013 The Zorba Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.bbytes.zorba.jobworker.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.domain.IJob;
import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobStatusType;
import com.bbytes.zorba.exception.JobExecutionException;
import com.bbytes.zorba.jobworker.JobProcessor;
import com.bbytes.zorba.jobworker.domain.JobExecutionContext;
import com.bbytes.zorba.jobworker.domain.ZorbaData;
import com.bbytes.zorba.jobworker.event.IJobEventPublisher;
import com.bbytes.zorba.jobworker.exception.ProcessingException;

/**
 * Default implementation of {@link JobProcessor} for processing the Jobs
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.1
 */
public class JobProcessorImpl implements JobProcessor {

	private Map<String, Class<IJob>> jobMap;

	private static final Logger log = Logger.getLogger(JobProcessorImpl.class);
	
	@Autowired
	private IJobEventPublisher eventPublisher;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.jobworker.JobProcessor#processJob(java.lang.String, java.util.Map)
	 */
	@Override
	public void processJob(String jobExecutionId, String jobName, ZorbaData<String, Serializable> jobData, JobExecutionContext executionContext) throws ProcessingException {
		if (jobName == null) {
			String message = "jobName is null";
			log.error(message);
			throw new ProcessingException(message);
		}
		Class<IJob> jobClass = jobMap.get(jobName);
		if(jobClass == null) {
			JobEvent failed = new JobEvent(jobExecutionId, JobStatusType.FAILED, null, this);
			String description = "Class for job name "+ jobName+" not registered";
			failed.setDescription(description);
			failed.setExecutionContext(executionContext);
			eventPublisher.publish(failed);
			log.error(description);
			throw new ProcessingException(description);
		}
		IJob job = null;
		try {
			job = jobClass.newInstance();
			JobEvent started = new JobEvent(jobExecutionId, JobStatusType.RUNNNING, job, this);
			started.setExecutionContext(executionContext);
			eventPublisher.publish(started);
			//execute the job
			job.execute(jobData);
			//publish the event after execution
			JobEvent finished = new JobEvent(jobExecutionId, JobStatusType.COMPLETED, job, this);
			finished.setExecutionContext(executionContext);
			eventPublisher.publish(finished);
		} catch (InstantiationException e) {
			JobEvent failed = new JobEvent(jobExecutionId, JobStatusType.FAILED, job, this);
			failed.setExecutionContext(executionContext);
			eventPublisher.publish(failed);
			log.error(e.getMessage(), e);
			throw new ProcessingException(e);
		} catch (IllegalAccessException e) {
			JobEvent failed = new JobEvent(jobExecutionId, JobStatusType.FAILED, job, this);
			eventPublisher.publish(failed);
			log.error(e.getMessage(), e);
			throw new ProcessingException(e);
		} catch (JobExecutionException e) {
			JobEvent failed = new JobEvent(jobExecutionId, JobStatusType.FAILED, job, this);
			eventPublisher.publish(failed);
			log.error(e.getMessage(), e);
			throw new ProcessingException(e);
		}
	}

	@Override
	public Map<String, Class<IJob>> getJobMap() throws ProcessingException {
		return jobMap;
	}

	@Override
	public void setJobMap(Map<String, Class<IJob>> jobMap) throws ProcessingException {
		this.jobMap = jobMap;		
	}

}
