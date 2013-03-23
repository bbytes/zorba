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
package com.bbytes.zorba.jobworker.impl;

import java.util.Map;

import com.bbytes.zorba.jobworker.JobProcessor;
import com.bbytes.zorba.jobworker.exception.ProcessingException;

/**
 * 
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
public class JobProcessorImpl implements JobProcessor {

	/* (non-Javadoc)
	 * @see com.bbytes.zorba.jobworker.JobProcessor#processJob(java.lang.String, java.util.Map)
	 */
	@Override
	public void processJob(String jobName, Map<String, ?> jobData) throws ProcessingException {
		// TODO Auto-generated method stub

	}

}