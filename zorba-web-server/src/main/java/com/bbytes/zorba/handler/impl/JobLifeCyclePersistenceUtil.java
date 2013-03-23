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
package com.bbytes.zorba.handler.impl;

import java.util.Date;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobLifeCycle;

/**
 * 
 * 
 * @author Thanneer
 * 
 * @version
 */
public class JobLifeCyclePersistenceUtil {

	public static JobLifeCycle onJobStartedEvent(JobEvent jobEvent) {

		JobLifeCycle jobLifeCycle = new JobLifeCycle();
		jobLifeCycle.setId(jobEvent.getJobExecutionId());
		jobLifeCycle.setJobExecutionId(jobEvent.getJobExecutionId());
		jobLifeCycle.setJob(jobEvent.getJob());
		jobLifeCycle.setCreationDate(new Date());
		jobLifeCycle.setCurrentStatus(jobEvent.getJobStatus());
		jobLifeCycle.setJobStartTime(jobEvent.getEventTime());
		jobLifeCycle.setDescription(jobEvent.getDescription());
		return jobLifeCycle;

	}

	public static JobLifeCycle onJobCompletedEvent(JobEvent jobEvent, JobLifeCycle jobLifeCycle) {
		if (jobLifeCycle != null) {
			jobLifeCycle.setCurrentStatus(jobEvent.getJobStatus());
			jobLifeCycle.setJobEndTime(jobEvent.getEventTime());
		}
		return jobLifeCycle;
	}
	
	public static JobLifeCycle onJobFailedEvent(JobEvent jobEvent, JobLifeCycle jobLifeCycle) {
		if (jobLifeCycle != null) {
			jobLifeCycle.setStackTrace(jobEvent.getStackTrace());
			jobLifeCycle.setCurrentStatus(jobEvent.getJobStatus());
			jobLifeCycle.setJobEndTime(jobEvent.getEventTime());
		}
		return jobLifeCycle;
	}

	public static JobLifeCycle onJobWaitingEvent(JobEvent jobEvent, JobLifeCycle jobLifeCycle) {
		if (jobLifeCycle != null) {
			jobLifeCycle.setCurrentStatus(jobEvent.getJobStatus());
		}
		return jobLifeCycle;
	}
	
	public static JobLifeCycle onJobRunningEvent(JobEvent jobEvent, JobLifeCycle jobLifeCycle) {
		if (jobLifeCycle != null) {
			jobLifeCycle.setCurrentStatus(jobEvent.getJobStatus());
		}
		return jobLifeCycle;
	}

	public static JobLifeCycle onJobTerminatedEvent(JobEvent jobEvent, JobLifeCycle jobLifeCycle) {
		if (jobLifeCycle != null) {
			jobLifeCycle.setCurrentStatus(jobEvent.getJobStatus());
			jobLifeCycle.setJobEndTime(jobEvent.getEventTime());
		}
		return jobLifeCycle;
	}
	
	public static JobLifeCycle onJobInterruptedEvent(JobEvent jobEvent, JobLifeCycle jobLifeCycle) {
		if (jobLifeCycle != null) {
			jobLifeCycle.setCurrentStatus(jobEvent.getJobStatus());
			jobLifeCycle.setJobEndTime(jobEvent.getEventTime());
		}
		return jobLifeCycle;
	}
}
