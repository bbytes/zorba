/**
 * 
 */
package com.bbytes.zorba.persistence;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.PriorityQueue;

/**
 * Dao interface for {@link IPriorityQueue}
 * 
 * @author Dhanush Gopinath
 *
 */
public interface IPriorityQueueDao extends IZorbaDao<PriorityQueue>{

	/**
	 * Returns the priority queues based on the priority
	 * 
	 * @param priority
	 * @return
	 */
	PriorityQueue getPriorityQueue(Priority priority);
}
