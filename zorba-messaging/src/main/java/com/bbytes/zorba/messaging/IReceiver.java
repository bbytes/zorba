package com.bbytes.zorba.messaging;

import com.bbytes.zorba.jobworker.domain.Priority;
import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * A consumer service that will listen to the queue and process the messages
 * arriving
 * 
 * @author Dhanush Gopinath
 * 
 */
public interface IReceiver {

	/**
	 * Receives message from a specific queue
	 * @throws MessagingException
	 */
	public void receive(String queueName) throws MessagingException;
	
	/**
	 * Receives message from a priority queue
	 * @param priorityQueue
	 * @throws MessagingException
	 */
	public void receive(Priority priorityQueue) throws MessagingException;

}
