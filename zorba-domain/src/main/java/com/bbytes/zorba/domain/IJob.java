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
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bbytes.zorba.exception.JobExecutionException;
import com.bbytes.zorba.jobworker.domain.ZorbaData;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;

/**
 * Interface definition for job to be registered in Zorba
 * 
 * @author Dhanush Gopinath
 * @since 0.0.1
 * @version 0.0.1
 * 
 */
@Component
public interface IJob {

	/**
	 * Returns the job name , this name is used to register the job so it should be unique or will
	 * get duplicate job name exception while registering
	 * 
	 * @return
	 */
	public String getJobName();

	/**
	 * Returns the job desc , this desc will be shown in web interface for clients to understand
	 * about this job impl
	 * 
	 * @return
	 */
	public String getJobDescription();

	/**
	 * The data set on {@link ZorbaRequest} is given to this method by the framework while executing
	 * the job
	 * 
	 * @param data
	 * @throws JobExecutionException
	 */
	public void execute(Map<String, ? extends Serializable> data) throws JobExecutionException;

	/**
	 * Returns back the result as {@link ZorbaData}
	 * 
	 * @return
	 */
	ZorbaData<String, Serializable> getResult();

	/**
	 * Sets the result for this job;
	 * 
	 * @param result
	 */
	void setResult(ZorbaData<String, Serializable> result);

}
