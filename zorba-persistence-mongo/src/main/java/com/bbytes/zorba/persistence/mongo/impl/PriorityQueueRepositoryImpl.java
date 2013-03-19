/*
 * Copyright (C) 2013 The Zorba Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.bbytes.zorba.persistence.mongo.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.PriorityQueue;
import com.bbytes.zorba.persistence.IPriorityQueueDao;

/**
 * Custom Repository implementation of the interface method defined in {@link IPriorityQueueDao}
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 * 
 */
public class PriorityQueueRepositoryImpl extends ZorbaRepositoryImpl<PriorityQueue> implements IPriorityQueueDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bbytes.zorba.persistence.IPriorityQueueDao#getPriorityQueue(com.bbytes.zorba.domain.Priority
	 * )
	 */
	@Override
	public PriorityQueue getPriorityQueue(Priority priority) {
		return mongoTemplate.findOne(Query.query(Criteria.where("priority").is(priority)), PriorityQueue.class);
	}

}
