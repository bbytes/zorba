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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.testing.ZorbaBaseTesting;
import com.bbytes.zorba.jobworker.JobProcessor;
import com.bbytes.zorba.jobworker.domain.ZorbaData;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.exception.ProcessingException;

/**
 * 
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/zorba-job-worker-test-context.xml" })
public class JobProcessorImplTest extends ZorbaBaseTesting {

	@Autowired
	private JobProcessor jobProcessor;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.bbytes.zorba.jobworker.impl.JobProcessorImpl#processJob(java.lang.String, java.lang.String, ZorbaData)}.
	 * @throws ProcessingException 
	 */
	@Test
	public void testProcessJob() throws ProcessingException {
		ZorbaRequest req = createZorbaRequestForSendMailJob();
		jobProcessor.processJob(req.getId(), req.getJobName(), req.getData());
	}
}
