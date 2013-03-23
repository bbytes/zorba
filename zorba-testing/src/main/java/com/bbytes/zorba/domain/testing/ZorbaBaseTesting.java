/*
 * Copyright (C) 2013 The Zorba Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.bbytes.zorba.domain.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.PriorityQueue;
import com.bbytes.zorba.job.SendMailJob;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;

/**
 * Base class for creating all the Mock Objects.
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.1
 */
public class ZorbaBaseTesting {

	@Autowired
	protected MongoTemplate mongoTemplate;

	protected void insertPriorityQueues() {
		PriorityQueue pq = new PriorityQueue();
		pq.setPriority(Priority.LOW);
		pq.setQueueName("pq.low.test");
		mongoTemplate.save(pq);

		pq = new PriorityQueue();
		pq.setPriority(Priority.HIGH);
		pq.setQueueName("pq.high.test");
		mongoTemplate.save(pq);

		pq = new PriorityQueue();
		pq.setPriority(Priority.CRITICAL);
		pq.setQueueName("pq.critical.test");
		mongoTemplate.save(pq);

		pq = new PriorityQueue();
		pq.setPriority(Priority.MEDIUM);
		pq.setQueueName("pq.medium.test");
		mongoTemplate.save(pq);

	}

	/**
	 * Returns a mock object for {@link SendMailJob}
	 * @return
	 */
	public static ZorbaRequest createZorbaRequestForSendMailJob() {
		ZorbaRequest mock = new ZorbaRequest();
		mock.setJobName("Send-Mail-Job");
		mock.setPriority(Priority.CRITICAL);
		mock.setId(UUID.randomUUID().toString());
		Map<String, String> data = new HashMap<String, String>();
		data.put("from", "dhanush@beyondbytes.co.in");
		data.put("to", "dhanush@beyondbytes.co.in");
		data.put("subject", "Unit Test Mail");
		data.put("body", "Unit Test Mail");
		mock.setData(data);
		return mock;
	}
}
