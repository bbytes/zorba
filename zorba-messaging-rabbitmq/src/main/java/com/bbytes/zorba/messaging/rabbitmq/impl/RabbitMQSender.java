/**
 * 
 */
package com.bbytes.zorba.messaging.rabbitmq.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
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
	
	private String jobEventQueue = null;
	

	public void send(final ZorbaRequest request, Priority priority)
			throws MessagingException {
		if(request!=null && priority!=null) {
			String queueName = priority.getQueueName();
			send(request, queueName);
		}
	}

	public void send(final ZorbaRequest request, String queueName)
			throws MessagingException {
		if(request!=null && queueName!=null) {
			final String replyQueue = queueName+".reply";
			rabbitOperations.convertAndSend(queueName, request, new MessagePostProcessor() {
				public Message postProcessMessage(Message message) throws AmqpException {
					message.getMessageProperties().setReplyTo(replyQueue);
					try {
						message.getMessageProperties().setCorrelationId(request.getId().getBytes("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						throw new AmqpException(e);
					}
					return message;
				}
			});
		}
	}

	public ZorbaResponse receiveResponse(String queueName) throws MessagingException {
		ZorbaResponse response = (ZorbaResponse) rabbitOperations.receiveAndConvert(queueName);
		return response;
	}

	public ZorbaResponse receiveResponse(Priority priority)
			throws MessagingException {
		if( priority!=null) {
			String queueName = priority.getQueueName();
			return receiveResponse(queueName);
		}
		return null;
	}

	@Override
	public void sendJobEvent(JobEvent jobEvent) throws MessagingException {
		rabbitOperations.convertAndSend(jobEventQueue, jobEvent);
	}
	
	public String getJobEventQueue() {
		return jobEventQueue;
	}
	
	public void setJobEventQueue(String jobEventQueue) {
		this.jobEventQueue = jobEventQueue;
	}

}
