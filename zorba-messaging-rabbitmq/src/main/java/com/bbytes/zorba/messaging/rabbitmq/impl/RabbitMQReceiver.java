package com.bbytes.zorba.messaging.rabbitmq.impl;

import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
import com.bbytes.zorba.messaging.IReceiver;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.IRabbitMQReceiver;

/**
 * {@link IReceiver} implementation for RabbitMQ
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 * 
 */
public class RabbitMQReceiver implements IRabbitMQReceiver {

	@Autowired
	private RabbitOperations rabbitOperations;


	public ZorbaRequest receive(String queueName) throws MessagingException {
		ZorbaRequest request = null;
		if (queueName != null) {
			request = (ZorbaRequest) rabbitOperations.receiveAndConvert(queueName);
		}
		return request;
	}

	public ZorbaRequest receive(Priority priority) throws MessagingException {
		ZorbaRequest request = null;
		if (priority != null) {
			String queueName = priority.getQueueName();
			request = receive(queueName);
		}
		return request;
	}

	public void sendResponse(ZorbaResponse response, String queueName) throws MessagingException {
		if (response != null && queueName != null) {
			rabbitOperations.convertAndSend(queueName, response);
		}

	}

	public void sendResponse(ZorbaResponse response, Priority priority) throws MessagingException {
		if (response != null && priority != null) {
			String queueName = priority.getQueueName();
			rabbitOperations.convertAndSend(queueName, response);
		}

	}

}
