/**
 * 
 */
package com.bbytes.zorba.domain.mongo;

import com.bbytes.zorba.domain.IPriorityQueue;
import com.bbytes.zorba.domain.Priority;

/**
 * @author dhanush
 * 
 */
public class PriorityQueue extends Entity implements IPriorityQueue {

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
