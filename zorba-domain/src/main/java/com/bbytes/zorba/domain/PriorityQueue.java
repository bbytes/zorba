/**
 * 
 */
package com.bbytes.zorba.domain;


/**
 * Domain entity for holding a priority queue
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 * 
 */
public class PriorityQueue extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String queueName;

	private Priority priority;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

}
