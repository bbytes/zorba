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
import org.springframework.amqp.support.converter.JsonMessageConverter;

import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.exception.ProcessingException;
import com.bbytes.zorba.jobworker.service.ZorbaRequestDelegationService;
import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * Handles all the Zorba Requests arriving in the queues created by the clients
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
public class ZorbaRuntimeRequestHandlerImpl extends AbstractZorbaRequestHandler {

	private static final Logger LOG = Logger.getLogger(ZorbaRuntimeRequestHandlerImpl.class);

	private ZorbaRequestDelegationService zorbaRquestDelegationService;

	public ZorbaRuntimeRequestHandlerImpl(JsonMessageConverter messageConverter,
			ZorbaRequestDelegationService zorbaRquestDelegationService) {
		this.jsonMessageConverter = messageConverter;
		this.zorbaRquestDelegationService = zorbaRquestDelegationService;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bbytes.zorba.messaging.rabbitmq.listener.ZorbaRequestHandler#handleZorbaRequest(com.bbytes
	 * .zorba.jobworker.domain.ZorbaRequest)
	 */
	@Override
	public void handleZorbaRequest(ZorbaRequest request) throws MessagingException {
		if (request == null)
			return;
		LOG.debug("Obtained Message" + request.getId());
		try {
			zorbaRquestDelegationService.processZorbaRequest(request);
		} catch (ProcessingException e) {
			LOG.error(e.getMessage(), e);
			throw new MessagingException(e);
		}

	}
}
