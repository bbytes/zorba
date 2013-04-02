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
package com.bbytes.zorba.messaging;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * A producer service interface to send the message to the rabbitmq queue.
 * 
 * @author Dhanush Gopinath
 * @version 1.0
 * 
 */
public interface ISender {

	/**
	 * Sends the request to the Queue based on the priority
	 * 
	 * @param request
	 * @throws MessagingException
	 */
	void send(final ZorbaRequest request, Priority priority) throws MessagingException;

	/**
	 * Sends the request to the specified Queue
	 * 
	 * @param request
	 * @throws MessagingException
	 */
	void send(final ZorbaRequest request, String queueName) throws MessagingException;

	/**
	 * Receives the response from the response queue
	 * 
	 * @param queueName
	 * @return {@link ZorbaResponse}
	 * @throws MessagingException
	 */
	ZorbaResponse receiveResponse(String queueName) throws MessagingException;

	/**
	 * Receives the response from the response queue by priority
	 * 
	 * @param queueName
	 * @return {@link ZorbaResponse}
	 * @throws MessagingException
	 */
	ZorbaResponse receiveResponse(Priority priority) throws MessagingException;
}
