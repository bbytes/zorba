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
package com.bbytes.zorba.jobworker.service.impl;

import java.util.Date;

import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bbytes.zorba.domain.IPriorityQueue;
import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.mongo.PriorityQueue;
import com.bbytes.zorba.jobworker.service.IPriorityQueueIdentifierService;
import com.bbytes.zorba.persistence.IPriorityQueueDao;
import com.bbytes.zorba.persistence.exception.PersistenceException;

/**
 * 
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
@Service(value="priorityQueueIdentifierService")
public class PriorityQueueIdentifierService implements IPriorityQueueIdentifierService {

	@Autowired
	@Qualifier("priorityQueueRepositoryImpl")
	private IPriorityQueueDao priorityQueueDao;
	
	/* (non-Javadoc)
	 * @see com.bbytes.zorba.jobworker.service.IPriorityQueueIdentifierService#getQueueName(com.bbytes.zorba.domain.Priority)
	 */
	@Override
	public String getQueueName(Priority priority) {
		return priorityQueueDao.getPriorityQueue(priority).getQueueName();
	}

	/* (non-Javadoc)
	 * @see com.bbytes.zorba.jobworker.service.IPriorityQueueIdentifierService#registerPriorityQueue(com.bbytes.zorba.domain.Priority, java.lang.String)
	 */
	@Override
	public void registerPriorityQueue(Priority priority, String queueName) {
		IPriorityQueue pq = new PriorityQueue();
		pq.setPriority(priority);
		pq.setQueueName(queueName);
		pq.setCreationDate(new Date());
		try {
			priorityQueueDao.create(pq);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
