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

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bbytes.zorba.domain.JobLifeCycle;
import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.job.CustomAddress;
import com.bbytes.zorba.job.SendMailJob;
import com.bbytes.zorba.jobworker.domain.ZorbaData;
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

	protected void insertJobLifeCycleObjects() {
		JobLifeCycle jobLifeCycle = new JobLifeCycle();
		jobLifeCycle.setId("1");
		jobLifeCycle.setCreationDate(new Date());
		jobLifeCycle.setJobExecutionId("test1");
		mongoTemplate.save(jobLifeCycle);

		JobLifeCycle jobLifeCycle2 = new JobLifeCycle();
		jobLifeCycle2.setId("2");
		jobLifeCycle2.setCreationDate(new Date());
		jobLifeCycle2.setJobExecutionId("test2");
		mongoTemplate.save(jobLifeCycle2);

	}

	/**
	 * Returns a mock object for {@link SendMailJob}
	 * 
	 * @return
	 */
	public static ZorbaRequest createZorbaRequestForSendMailJob() {
		ZorbaRequest mock = new ZorbaRequest();
		mock.setJobName("Send-Mail-Job");
		mock.setPriority(Priority.CRITICAL);
		mock.setId(UUID.randomUUID().toString());
		ZorbaData<String,Serializable> data = new ZorbaData<String,Serializable>();
		data.put("from", "dhanush@beyondbytes.co.in");
		data.put("to", "dhanush@beyondbytes.co.in");
		data.put("subject", "Unit Test Mail");
		data.put("body", "Unit Test Mail");
		mock.setData(data);
		return mock;
	}

	/**
	 * Returns a mock object for {@link SendMailJob}
	 * 
	 * @return
	 */
	public static ZorbaRequest createZorbaRequestForSendMailJob(Priority p) {
		ZorbaRequest mock = new ZorbaRequest();
		mock.setJobName("Send-Mail-Job");
		mock.setPriority(p);
		mock.setId(UUID.randomUUID().toString());
		ZorbaData<String,Serializable> data = new ZorbaData<String,Serializable>();
		data.put("from", "dhanush@beyondbytes.co.in");
		data.put("to", "dhanush@beyondbytes.co.in");
		data.put("subject", "Unit Test Mail with Priority");
		data.put("body", "Unit Test Mail");
		mock.setData(data);
		return mock;
	}

	/**
	 * Returns a mock object for {@link SendMailJob} with {@link CustomAddress} object set in the
	 * {@link ZorbaData} of the job
	 * 
	 * @param p
	 * @return
	 */
	public static ZorbaRequest createZorbaRequestForSendMailJobWithAddress(Priority p) {
		ZorbaRequest mock = new ZorbaRequest();
		mock.setJobName("Send-Mail-Job");
		mock.setPriority(p);
		mock.setId(UUID.randomUUID().toString());
		ZorbaData<String,Serializable> data = new ZorbaData<String,Serializable>();
		data.put("from", "dhanush@beyondbytes.co.in");
		data.put("to", "dhanush@beyondbytes.co.in");
		data.put("subject", "Unit Test Mail");
		data.put("body", "Unit Test Mail");
		data.put("an_array", new String [] {"1", "2"});
		data.put("address", createDefaultCustomAddress());
		mock.setData(data);
		return mock;
	}

	/**
	 * Creates the default custom address
	 * @return
	 */
	public static CustomAddress createDefaultCustomAddress() {
		CustomAddress address = new CustomAddress();
		address.setDistrict("Bangalore");
		address.setHouseNo(23);
		address.setLocality("OMBR");
		address.setState(CustomAddress.State.KA);
		address.setStreet("3rs Main");
		address.setTown("Bangalore");
		address.setZipCode(12344);
		return address;
	}

}
