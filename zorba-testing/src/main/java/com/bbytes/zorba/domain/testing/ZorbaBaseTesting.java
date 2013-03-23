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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobLifeCycle;
import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.PriorityQueue;

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
		JobLifeCycle jobLifeCycle= new JobLifeCycle();
		jobLifeCycle.setId("1");
		jobLifeCycle.setCreationDate(new Date());
		jobLifeCycle.setJobExecutionId("test1");
		mongoTemplate.save(jobLifeCycle);
		
		JobLifeCycle jobLifeCycle2= new JobLifeCycle();
		jobLifeCycle2.setId("2");
		jobLifeCycle2.setCreationDate(new Date());
		jobLifeCycle2.setJobExecutionId("test2");
		mongoTemplate.save(jobLifeCycle2);
		
	}
}
