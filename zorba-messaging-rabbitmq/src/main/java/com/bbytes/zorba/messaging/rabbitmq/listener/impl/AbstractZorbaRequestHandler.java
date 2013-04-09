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
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.listener.ZorbaRequestHandler;

/**
 * Abstract class for {@link ZorbaRequestHandler}. Implements
 * {@link MessageListener#onMessage(Message)} method.
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.2
 * @since 0.0.2
 */
public abstract class AbstractZorbaRequestHandler implements ZorbaRequestHandler {

	private static final Logger LOG = Logger.getLogger(AbstractZorbaRequestHandler.class);
	
	@Autowired
	protected JsonMessageConverter jsonMessageConverter;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.amqp.core.MessageListener#onMessage(org.springframework.amqp.core.Message
	 * )
	 */
	@Override
	public void onMessage(Message message) {
		ZorbaRequest request = (ZorbaRequest) jsonMessageConverter.fromMessage(message);
		try {
			handleZorbaRequest(request);
		} catch (MessagingException e) {
			LOG.error("Error in Handling the request", e);
		}

	}

}
