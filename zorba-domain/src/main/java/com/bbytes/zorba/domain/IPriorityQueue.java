/**
 * 
 */
package com.bbytes.zorba.domain;

/**
 * Interface for encapsulating a queue name against its priority
 *  
 * @author Dhanush Gopinath
 *
 */
public interface IPriorityQueue extends IEntity{

	/**
	 * Returns the queue name
	 * @return
	 */
	String getQueueName();
	/**
	 * Returns the priority
	 * @return
	 */
	Priority getPriority();
	
	/**
	 * Sets the queue name
	 */
	void setQueueName(String queueName);
	/**
	 * Sets the priority
	 */
	void setPriority(Priority priority);
	
}
