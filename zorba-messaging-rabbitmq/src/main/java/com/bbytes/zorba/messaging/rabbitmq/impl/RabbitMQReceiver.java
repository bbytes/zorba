package com.bbytes.zorba.messaging.rabbitmq.impl;

import com.bbytes.zorba.jobworker.domain.Priority;
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

	public void receive(String queueName) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	public void receive(Priority priorityQueue) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	public void sendResponse(ZorbaResponse response, String queueName)
			throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	public void sendResponse(ZorbaResponse response, Priority priorityQueue)
			throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	
}
