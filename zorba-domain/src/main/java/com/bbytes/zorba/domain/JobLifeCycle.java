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

/**
 * Domain object that holds the life cycle information of an {@link IJob}
 * 
 * @author Thanneer
 * 
 * @version
 */
public class JobLifeCycle extends Entity {

	private static final long serialVersionUID = 1409556653923929937L;

	// might need id for quick retrieval in some persistence impl
	private String jobExecutionId;

	private IJob job;

	private Date jobStartTime;

	private Date jobEndTime;

	private Date runDuration;

	private JobStatusType currentStatus;
	
	private String description;

	// to store error information incase job failed
	private String stackTrace;

	

	/**
	 * @return the jobExecutionId
	 */
	public String getJobExecutionId() {
		return jobExecutionId;
	}

	/**
	 * @param jobExecutionId the jobExecutionId to set
	 */
	public void setJobExecutionId(String jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	/**
	 * @return the job
	 */
	public IJob getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(IJob job) {
		this.job = job;
	}

	/**
	 * @return the jobStartTime
	 */
	public Date getJobStartTime() {
		return jobStartTime;
	}

	/**
	 * @param jobStartTime
	 *            the jobStartTime to set
	 */
	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	/**
	 * @return the jobEndTime
	 */
	public Date getJobEndTime() {
		return jobEndTime;
	}

	/**
	 * @param jobEndTime
	 *            the jobEndTime to set
	 */
	public void setJobEndTime(Date jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	/**
	 * @return the runDuration
	 */
	public Date getRunDuration() {
		return runDuration;
	}

	/**
	 * @param runDuration
	 *            the runDuration to set
	 */
	public void setRunDuration(Date runDuration) {
		this.runDuration = runDuration;
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
	 * @return the currentStatus
	 */
	public JobStatusType getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus
	 *            the currentStatus to set
	 */
	public void setCurrentStatus(JobStatusType currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
