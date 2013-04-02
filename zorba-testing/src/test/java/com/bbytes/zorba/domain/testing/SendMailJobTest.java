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
package com.bbytes.zorba.domain.testing;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.exception.JobExecutionException;
import com.bbytes.zorba.job.SendMailJob;

/**
 * 
 *
 * @author Thanneer
 *
 * @version 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:spring/zorba-testing-context.xml" })
public class SendMailJobTest extends ZorbaBaseTesting{
	
	@Test
	public void sendMail() throws JobExecutionException
	{
		SendMailJob sendMailJob = new SendMailJob();
		Map<String,String> data = new HashMap<String, String>();
		data.put("from", "zorba.jobserver@gmail.com");
		data.put("to", "tm@beyondbytes.co.in");
		data.put("subject", "Zorba job id " + UUID.randomUUID().toString());
		data.put("body", "Zorba message body... date with time : " + new Date());
		sendMailJob.execute(data);
	}
}
