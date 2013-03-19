/**
 * 
 */
package com.bbytes.zorba.messaging;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * A producer service interface to send the message to the rabbitmq queue.
 * 
 * @author Dhanush Gopinath
 * @version 1.0
 * 
 */
@Deprecated
// Sender not required in server side ,only client should have sender interface impl
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

	/**
	 * Receives the response from the response queue
	 * 
	 * @param queueName
	 * @return TODO
	 * @throws MessagingException
	 */
	ZorbaResponse receiveResponse(String queueName) throws MessagingException;

	/**
	 * Receives the response from the response queue by priority
	 * 
	 * @param queueName
	 * @return TODO
	 * @throws MessagingException
	 */
	ZorbaResponse receiveResponse(Priority priority) throws MessagingException;
}
