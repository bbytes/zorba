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
package com.bbytes.zorba.messaging.rabbitmq.listener.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.domain.IJob;
import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobStatusType;
import com.bbytes.zorba.jobworker.domain.JobExecutionContext;
import com.bbytes.zorba.jobworker.domain.ZorbaData;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
import com.bbytes.zorba.jobworker.event.IJobEventListener;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.IRabbitMQReceiver;
import com.bbytes.zorba.messaging.rabbitmq.IRabbitMQSender;

/**
 * The default job event listener that publishes all the event to the event queue.The events form
 * the event queue is processed at web server level and stored in persistence layer like mongo db
 * 
 * @author Thanneer
 * 
 * @version
 */
public class JobEventListenerImpl implements IJobEventListener {

	@Autowired
	private IRabbitMQSender rabbitMQSender;

	@Autowired
	private IRabbitMQReceiver rabbitMQReceiver;

	private String jobEventQueue = null;

	private static final Logger log = Logger.getLogger(JobEventListenerImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context
	 * .ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(JobEvent event) {
		JobStatusType jobStatus = event.getJobStatus();
		log.debug("Received job event with event status:- " + jobStatus);
		try {
			JobExecutionContext executionContext = event.getExecutionContext();
			switch (jobStatus) {
			// Just send the event to the job event queue for all the cases except completed
			case STARTED:
			case FAILED:
			case WAITING:
			case RUNNNING:
			case INTERRUPTED:
				// FIXME: setting the source as null, as there are no serializers for the event
				// source as there are issue in sending it to the queue. May need a solution for
				// this
				rabbitMQSender.sendJobEvent(event);
				break;
			case COMPLETED:
				// for completed - send the job event without the result to the job event queue and
				// the result to response queue as a ZorbaResponse
				IJob job = event.getJob();
				if (executionContext != null) {
					//send response only if the job execution context is set
					ZorbaResponse response = createZorbaResponse(event.getJobExecutionId(),job);
					String queueName = executionContext.getRequestQueueName();
					if (queueName == null) {
						queueName = executionContext.getPriority().getQueueName();
					}
					log.debug("Sending response for the job :: " + job.getJobName());
					rabbitMQReceiver.sendResponse(response, queueName + ".reply");
				}
				job.setResult(null);
				rabbitMQSender.sendJobEvent(event);
			}
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * Creates the {@link ZorbaResponse} from {@link IJob}
	 * @param responseId 
	 * 
	 * @param job
	 * @return
	 */
	private ZorbaResponse createZorbaResponse(String responseId, IJob job) {
		ZorbaData<String, Serializable> result = job.getResult();
		ZorbaResponse response = new ZorbaResponse();
		response.setId(responseId);
		response.setJobName(job.getJobName());
		response.setResult(result);
		return response;
	}

	public String getJobEventQueue() {
		return jobEventQueue;
	}

	public void setJobEventQueue(String jobEventQueue) {
		this.jobEventQueue = jobEventQueue;
	}

}
