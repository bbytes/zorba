/**
 * 
 */
package com.bbytes.zorba.persistence;

import com.bbytes.zorba.domain.IPriorityQueue;
import com.bbytes.zorba.domain.Priority;

/**
 * Dao interface for {@link IPriorityQueue}
 * 
 * @author Dhanush Gopinath
 *
 */
public interface IPriorityQueueDao extends IZorbaDao<IPriorityQueue>{

	/**
	 * Returns the priority queues based on the priority
	 * 
	 * @param priority
	 * @return
	 */
	IPriorityQueue getPriorityQueue(Priority priority);
}
