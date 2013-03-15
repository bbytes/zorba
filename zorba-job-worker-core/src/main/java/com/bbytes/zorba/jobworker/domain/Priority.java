/**
 * 
 */
package com.bbytes.zorba.jobworker.domain;

/**
 * An {@link Enum} for priority of the queues
 * 
 * @author Dhanush Gopinath
 * @since 0.0.1
 * @version 0.0.1
 * 
 */
public enum Priority {

	CRITICAL(4), HIGH(3), MEDIUM(2), LOW(1);

	int priority;

	Priority(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
}
