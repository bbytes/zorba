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
package com.bbytes.zorba.messaging.rabbitmq.service;

import java.io.Serializable;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbytes.zorba.jobworker.domain.ZorbaData;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.messaging.IQueueAdminService;
import com.bbytes.zorba.messaging.rabbitmq.IRabbitMQSender;

/**
 * 
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
@Service
public class QueueAdminServiceImpl implements IQueueAdminService {

	private static final Logger LOG = Logger.getLogger(QueueAdminServiceImpl.class);

	@Autowired
	private IQueueServiceRest queueServiceRest;
	
	@Autowired
	private RabbitAdmin rabbitAdmin;
	
	@Autowired
	private IRabbitMQSender sender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.messaging.IQueueAdminService#createQueue(java.lang.String)
	 */
	@Override
	public boolean createQueue(String queueName) {
		try {
			org.springframework.amqp.core.Queue queue  = new org.springframework.amqp.core.Queue(queueName,true);
			rabbitAdmin.declareQueue(queue);
			//send message to the norify queue
			ZorbaRequest req = createZorbaRequest(queueName, "CREATED");
			sender.send(req, "zorba.queue.notify");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

	protected ZorbaRequest createZorbaRequest(String queueName, String event) {
		ZorbaRequest req = new ZorbaRequest();
		req.setId(UUID.randomUUID().toString());
		req.setQueueName("zorba.queue.notify");
		ZorbaData<String, Serializable> data = new ZorbaData<String, Serializable>();
		data.put("queueName", queueName);
		data.put("event", event);
		req.setData(data);
		return req;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.messaging.IQueueAdminService#deleteQueue(java.lang.String)
	 */
	@Override
	public boolean deleteQueue(String queueName) {
		try {
			rabbitAdmin.deleteQueue(queueName);
			ZorbaRequest req = createZorbaRequest(queueName, "DELETED");
			sender.send(req, "zorba.queue.notify");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.messaging.IQueueAdminService#deleteQueueContent(java.lang.String)
	 */
	@Override
	public boolean deleteQueueContent(String queueName) {
		try {
			queueServiceRest.deleteQueueContent(getVHost(), queueName);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}

		return true;

	}

	/**
	 * Returns default vhost for rabbitmq. Need to improve this method by taking the vhost thru
	 * configuration
	 * 
	 * @return
	 */
	public String getVHost() {
		return "%2F";
	}
}
