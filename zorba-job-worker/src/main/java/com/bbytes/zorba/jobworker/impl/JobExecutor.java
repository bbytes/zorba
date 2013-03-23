/*
 * Copyright (C) 2013 The Zorba Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.bbytes.zorba.jobworker.impl;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobStatusType;
import com.bbytes.zorba.jobworker.JobProcessor;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.event.IJobEventPublisher;
import com.bbytes.zorba.jobworker.exception.ProcessingException;

/**
 * Runnable interface implementation that will execute inside the {@link TaskExecutor}
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
public class JobExecutor implements Runnable {
	
	private static final Logger log = Logger.getLogger(JobExecutor.class);
	
	private ZorbaRequest request;
	private JobProcessor jobProcessor;
	private IJobEventPublisher eventPublisher;
	
	
	/**
	 * Constructor
	 * @param request
	 * @param jobProcessor
	 * @param eventPublisher
	 */
	public JobExecutor(ZorbaRequest request, JobProcessor jobProcessor, IJobEventPublisher eventPublisher) {
		super();
		this.request = request;
		this.jobProcessor = jobProcessor;
		this.eventPublisher = eventPublisher;
	}

	/**
	 * Constructor
	 * @param request
	 * @param jobProcessor
	 */
	public JobExecutor(ZorbaRequest request, JobProcessor jobProcessor) {
		super();
		this.request = request;
		this.jobProcessor = jobProcessor;
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			JobEvent started = new JobEvent(request.getId(), JobStatusType.STARTED, null, request.getJobName());
			eventPublisher.publish(started);
			jobProcessor.processJob(request.getId(), request.getJobName(), request.getData());
		} catch (ProcessingException e) {
			log.error(e.getMessage(), e);
		}
	}

}
