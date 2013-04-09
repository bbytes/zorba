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
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.jobworker.domain.ZorbaData;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.service.ZorbaRequestDelegationService;
import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * A {@link MessageListener} implementation which will handle the messages arriving in
 * zorba.notify.queue. Messages sent for creation of new Queues are handled by this class.
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.2
 */
public class QueueNotificationHandler extends AbstractZorbaRequestHandler {

	private static final Logger LOG = Logger.getLogger(QueueNotificationHandler.class);

	@Autowired
	private ConnectionFactory rabbitConnectionFactory;

	@Autowired
	private ZorbaRequestDelegationService zorbaRquestDelegationService;

	private Map<String, SimpleMessageListenerContainer> queueListeners = new HashMap<String, SimpleMessageListenerContainer>();

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
		ZorbaData<String, Serializable> data = request.getData();
		if (data == null) {
			LOG.error("No data received along with request " + request.getId());
			return;
		}
		String queueName = (String) data.get("queueName");
		if (queueName == null || queueName.isEmpty()) {
			LOG.error("No Queue Name received along with request " + request.getId());
			return;
		}
		String event = (String) data.get("event");
		if (event.equals("CREATED")) {
			LOG.debug("New Queue Created. Adding Listener to that");
			final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
			container.setAutoStartup(false);
			container.setConnectionFactory(rabbitConnectionFactory);
			container.setQueueNames(queueName);
			container.setMessageListener(new ZorbaRuntimeRequestHandlerImpl(jsonMessageConverter,
					zorbaRquestDelegationService));
			container.setConcurrentConsumers(3);
			queueListeners.put(queueName, container);
			// start the container
			Thread thread = new Thread() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					container.start();
				}
			};
			LOG.debug("Starting container for the queue " + queueName);
			thread.start();

		} else if (event.equals("DELETED")) {
			LOG.debug("Deleting container for the queue " + queueName);
			SimpleMessageListenerContainer container = queueListeners.get(queueName);
			if (container == null) {
				LOG.warn("SimpleMessageListenerContainer is null. Why?");
				return;
			}
			if (container.isRunning()) {
				container.stop();
			}
			container.destroy();
			queueListeners.remove(queueName);
		}

	}

}
