/**
 * 
 */
package com.bbytes.zorba.messaging;

import com.bbytes.zorba.jobworker.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * A producer service interface to send the message to the rabbitmq queue.
 * 
 * @author Dhanush Gopinath
 * @version 1.0
 * 
 */
public interface ISender {

	/**
	 * Sends the request to the Queue based on the priority
	 * 
	 * @param request
	 * @throws MessagingException
	 */
	void send(final ZorbaRequest request, Priority priority) throws MessagingException;

	/**
	 * Sends the request to the specified Queue 
	 * 
	 * @param request
	 * @throws MessagingException
	 */
	void send(final ZorbaRequest request, String queueName) throws MessagingException;
}