/**
 * 
 */
package com.bbytes.zorba.persistence.mongo.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bbytes.zorba.domain.IPriorityQueue;
import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.mongo.PriorityQueue;
import com.bbytes.zorba.persistence.IPriorityQueueDao;

/**
 * Custom
 * @author Dhanush Gopinath
 * @version 0.0.1
 *
 */
public class PriorityQueueRepositoryImpl extends ZorbaRepositoryImpl<IPriorityQueue> implements IPriorityQueueDao {

	
	/* (non-Javadoc)
	 * @see com.bbytes.zorba.persistence.IPriorityQueueDao#getPriorityQueue(com.bbytes.zorba.domain.Priority)
	 */
	@Override
	public IPriorityQueue getPriorityQueue(Priority priority) {
		return mongoTemplate.findOne(Query.query(Criteria.where("priority").is(priority)), PriorityQueue.class);
	}

}
