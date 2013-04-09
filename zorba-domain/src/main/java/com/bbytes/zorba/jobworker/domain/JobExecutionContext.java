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
package com.bbytes.zorba.jobworker.domain;

import java.io.Serializable;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.Priority;

/**
 * An execution context passed to the Job Processor. This will be set on the {@link JobEvent} as
 * well while firing the event.
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.1
 */
public class JobExecutionContext {

	private String requestQueueName;

	private Priority priority;
	
	private String correlationId;

	private ZorbaData<String, Serializable> contextData;

	public String getRequestQueueName() {
		return requestQueueName;
	}

	public void setRequestQueueName(String requestQueueName) {
		this.requestQueueName = requestQueueName;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void put(String key, Serializable value) {
		if (contextData == null) {
			contextData = new ZorbaData<String, Serializable>();
		}
		contextData.put(key, value);
	}

	public Serializable get(String key) {
		if (contextData == null) {
			return null;
		}
		return contextData.get(key);
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}
