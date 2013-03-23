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
package com.bbytes.zorba.jobworker.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * The domain class which encapsulates the response send back by zorba
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.1
 */
public class ZorbaResponse implements Serializable{


	private static final long serialVersionUID = -6754862309951736253L;

	protected String id;
	protected String jobName;
	protected Map<String, ?> result;
	protected String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Map<String, ?> getResult() {
		return result;
	}

	public void setResult(Map<String, ?> result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
