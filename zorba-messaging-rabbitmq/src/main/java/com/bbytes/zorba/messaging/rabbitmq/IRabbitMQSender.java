/**
 * 
 */
package com.bbytes.zorba.messaging.rabbitmq;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.messaging.ISender;
import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * Interface for RabbitMQ implementation, that extends {@link ISender}
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 *
 */
public interface IRabbitMQSender extends ISender {

	/**
	 * Sends the Job Event to the designated job event queue
	 * 
	 * @param jobEvent
	 * @throws MessagingException
	 */
	void sendJobEvent(JobEvent jobEvent)throws MessagingException;
}
