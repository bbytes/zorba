/**
 * 
 */
package com.bbytes.zorba.jobworker.service;

import com.bbytes.zorba.jobworker.domain.Priority;

/**
 * An interface that will return the name of the priority queue based on the Priority
 * 
 * @author Dhanush Gopinath
 * 
 *
 */
public interface IPriorityQueueIdentifierService {

	/**
	 * Returns the queue name based on the priority
	 * @param priority
	 * @return
	 */
	String getQueueName(Priority priority);
	
	/**
	 * Register's the priority queue name
	 * 
	 * @param priority
	 * @param queueName
	 */
	void registerPriorityQueue(Priority priority, String queueName);
}
