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

import java.util.Date;
import java.util.Map;

import com.bbytes.zorba.exception.JobExecutionException;

/**
 * Implements the basic method of {@link IJob} like creation date etc
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
public abstract class AbstractJob implements IJob {

	private static final long serialVersionUID = -4230206130759611641L;

	protected String jobId;

	protected Date creationDate;

	protected Date modificationDate;

	public AbstractJob() {
		creationDate = new Date();
		modificationDate = new Date();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IEntity#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		this.jobId = id;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IEntity#getId()
	 */
	@Override
	public String getId() {
		return jobId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IEntity#getCreationDate()
	 */
	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IEntity#setCreationDate(java.util.Date)
	 */
	@Override
	public void setCreationDate(Date creationDate) {
		// Don't set creation date as it is done while creating object but child classes can override
		// this behavior
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IEntity#getModificationDate()
	 */
	@Override
	public Date getModificationDate() {
		return modificationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbytes.zorba.domain.IEntity#setModificationDate(java.util.Date)
	 */
	@Override
	public void setModificationDate(Date modificationDate) {
		this.modificationDate=modificationDate;

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
	public abstract void execute(Map<String, ?> data) throws JobExecutionException ;

}
