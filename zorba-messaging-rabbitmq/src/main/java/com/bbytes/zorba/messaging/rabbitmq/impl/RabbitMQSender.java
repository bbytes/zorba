/**
 * 
 */
package com.bbytes.zorba.messaging.rabbitmq.impl;

import com.bbytes.zorba.jobworker.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
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

	/* (non-Javadoc)
	 * @see com.bbytes.zorba.messaging.ISender#send(com.bbytes.zorba.jobworker.domain.ZorbaRequest, com.bbytes.zorba.jobworker.domain.Priority)
	 */
	public void send(ZorbaRequest request, Priority priority)
			throws MessagingException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.bbytes.zorba.messaging.ISender#send(com.bbytes.zorba.jobworker.domain.ZorbaRequest, java.lang.String)
	 */
	public void send(ZorbaRequest request, String queueName)
			throws MessagingException {
		// TODO Auto-generated method stub

	}

	public void receiveResponse(String queueName) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	public void receiveResponse(Priority priorityQueue)
			throws MessagingException {
		// TODO Auto-generated method stub
		
	}

}
