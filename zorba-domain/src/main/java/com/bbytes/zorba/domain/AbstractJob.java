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
package com.bbytes.zorba.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.bbytes.zorba.exception.JobExecutionException;
import com.bbytes.zorba.jobworker.domain.ZorbaData;

/**
 * Implements the basic method of {@link IJob} like creation date etc
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
public abstract class AbstractJob implements IJob {


	private ZorbaData<String, Serializable> result = new ZorbaData<String, Serializable>();
	
	protected String jobId;

	protected Date creationDate;

	protected Date modificationDate;

	public AbstractJob() {
		creationDate = new Date();
		modificationDate = new Date();

	}
	public void setId(String id) {
		this.jobId = id;

	}
	public String getId() {
		return jobId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		// Don't set creation date as it is done while creating object but child classes can override
		// this behavior
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate=modificationDate;

	}

	@Override
	public ZorbaData<String, Serializable> getResult() {
		return result;
	}

	@Override
	public void setResult(ZorbaData<String, Serializable> result) {
		this.result = result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IJob#getJobName()
	 */
	@Override
	public abstract String getJobName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IJob#execute(java.util.Map)
	 */
	@Override
	public abstract void execute(Map<String, ? extends Serializable> data) throws JobExecutionException ;

}
