/**
 * 
 */
package com.bbytes.zorba.messaging.rabbitmq.impl;

import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
import com.bbytes.zorba.jobworker.service.IPriorityQueueIdentifierService;
import com.bbytes.zorba.messaging.ISender;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.IRabbitMQSender;

/**
 * Implementation of {@link ISender} for RabbitMQ
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 *
 */
public class RabbitMQSender implements IRabbitMQSender {
	
	@Autowired
	private RabbitOperations rabbitOperations;
	
	@Autowired
	private IPriorityQueueIdentifierService priorityQueueIdentifierService;


	public void send(ZorbaRequest request, Priority priority)
			throws MessagingException {
		if(request!=null && priority!=null) {
			String queueName = priorityQueueIdentifierService.getQueueName(priority);
			rabbitOperations.convertAndSend(queueName, request);
		}
	}

	public void send(ZorbaRequest request, String queueName)
			throws MessagingException {
		if(request!=null && queueName!=null) {
			rabbitOperations.convertAndSend(queueName, request);
		}
	}

	public ZorbaResponse receiveResponse(String queueName) throws MessagingException {
		ZorbaResponse response = (ZorbaResponse) rabbitOperations.receiveAndConvert(queueName);
		return response;
	}

	public ZorbaResponse receiveResponse(Priority priority)
			throws MessagingException {
		if( priority!=null) {
			String queueName = priorityQueueIdentifierService.getQueueName(priority);
			return receiveResponse(queueName);
		}
		return null;
	}

}
