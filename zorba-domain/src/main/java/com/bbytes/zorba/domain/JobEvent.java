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
package com.bbytes.zorba.domain;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.bbytes.zorba.jobworker.domain.JobExecutionContext;

/**
 * The class holds all the information related to job events like job started , ended etc
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
public class JobEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3825391489928185524L;

	private JobStatusType jobStatus;

	private JobExecutionContext executionContext;
	
	private IJob job;

	private String jobExecutionId;

	private Date eventTime = new Date();

	private String stackTrace;

	private String description;

	public JobEvent() {
		super("");
	}

	public JobEvent(String jobExecutionId, JobStatusType jobStatus, IJob job, Object eventObject) {
		super(eventObject);
		this.jobExecutionId = jobExecutionId;
		this.jobStatus = jobStatus;
		this.job = job;
	}

	/**
	 * @return the eventObject
	 */
	public Object getEventObject() {
		return getSource();
	}

	/**
	 * @return the job
	 */
	public IJob getJob() {
		return job;
	}

	/**
	 * @return the jobStatus
	 */
	public JobStatusType getJobStatus() {
		return jobStatus;
	}

	/**
	 * @return the jobExecutionId
	 */
	public String getJobExecutionId() {
		return jobExecutionId;
	}

	/**
	 * @return the eventTime
	 */
	public Date getEventTime() {
		return eventTime;
	}

	/**
	 * @return the stackTrace
	 */
	public String getStackTrace() {
		return stackTrace;
	}

	/**
	 * @param stackTrace
	 *            the stackTrace to set
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the {@link JobExecutionContext} object
	 * 
	 * @return
	 */
	public JobExecutionContext getExecutionContext() {
		return executionContext;
	}

	/**
	 * Sets the {@link JobExecutionContext}
	 * @param executionContext
	 */
	public void setExecutionContext(JobExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

}
