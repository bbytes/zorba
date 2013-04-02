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
package com.bbytes.zorba.event;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobStatusType;
import com.bbytes.zorba.persistence.JobLifeCycleDao;

/**
 * 
 * 
 * @author Thanneer
 * 
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/rootContext.xml" })
public class ZorbaJobEventQueueHandlerTest {

	@Autowired
	RabbitOperations rabbitOperations;

	@Resource(name = "${zorba.event.queue}")
	Queue eventQueue;
	
	@Autowired
	JobLifeCycleDao jobLifeCycleDao;


	@Before
	public void sendJobEvent() {
		JobEvent jobEvent = new JobEvent("test id 1", JobStatusType.STARTED, null, "simple object");
		rabbitOperations.convertAndSend(eventQueue.getName(), jobEvent);
	}
	
	@Test
	public void testEventHandler(){
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(jobLifeCycleDao.findOne("test id 1"));
	}
}
