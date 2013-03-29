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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.exception.ProcessingException;
import com.bbytes.zorba.jobworker.service.ZorbaRequestDelegationService;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.IRabbitMQReceiver;
import com.bbytes.zorba.messaging.rabbitmq.listener.ZorbaSynchRequestHandler;

/**
 * Implementation of {@link ZorbaSynchRequestHandler} for handling the messages arriving in the
 * priority queues
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
public class PriorityQueueSynchRequestHandlerImpl implements ZorbaSynchRequestHandler {

	private static final Logger log = Logger.getLogger(PriorityQueueSynchRequestHandlerImpl.class);
	
	@Autowired
	private ZorbaRequestDelegationService zorbaRquestDelegationService;
	
	@Autowired
	private IRabbitMQReceiver rabbitMQReceiver;

	
	public ZorbaRequestDelegationService getZorbaRquestDelegationService() {
		return zorbaRquestDelegationService;
	}


	public void setZorbaRquestDelegationService(ZorbaRequestDelegationService zorbaRquestDelegationService) {
		this.zorbaRquestDelegationService = zorbaRquestDelegationService;
	}

	public IRabbitMQReceiver getRabbitMQReceiver() {
		return rabbitMQReceiver;
	}


	public void setRabbitMQReceiver(IRabbitMQReceiver rabbitMQReceiver) {
		this.rabbitMQReceiver = rabbitMQReceiver;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bbytes.zorba.messaging.rabbitmq.listener.ZorbaSynchRequestHandler#handleZorbaRequests()
	 */
	@Override
	@Scheduled(fixedDelay = 30000)
	@Async
	public void handleZorbaRequests() throws MessagingException {
		handlePriorityRequests(Priority.CRITICAL);
		handlePriorityRequests(Priority.HIGH);
		handlePriorityRequests(Priority.MEDIUM);
		handlePriorityRequests(Priority.LOW);
	}


	/**
	 * Checks if the thread is available for the {@link Priority} and fetches the
	 * {@link ZorbaRequest}. The fetched request is given for processing
	 * 
	 * @param priority
	 * @throws MessagingException
	 */
	protected void handlePriorityRequests(Priority priority) throws MessagingException {
		try {
			if (zorbaRquestDelegationService.isThreadAvailable(priority)) {
				ZorbaRequest request = rabbitMQReceiver.receive(priority);
				if(request == null)
					return;
				zorbaRquestDelegationService.processZorbaRequest(request);
			}
		} catch (ProcessingException e) {
			log.error(e.getMessage(), e);
			throw new MessagingException(e);
		} catch (MessagingException e) {
			throw new MessagingException(e);
		}
	}
}
