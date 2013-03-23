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
package com.bbytes.zorba.jobworker.event;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobStatusType;

/**
 * 
 *
 * @author Thanneer
 *
 * @version 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:/spring/zorba-job-worker-test-context.xml" })
public class JobEventTest implements IJobEventListener{

	@Autowired
	IJobEventPublisher eventPublisher;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEventPublishAndListener(){
		JobEvent jobEvent = new JobEvent("testid",JobStatusType.STARTED, null, "Test event");
		StaticApplicationContext context=new StaticApplicationContext();
		context.addApplicationListener(this);
		context.refresh();
		context.publishEvent(jobEvent);
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(JobEvent event) {
		Assert.assertEquals("Test event", event.getEventObject());
		
	}
}
