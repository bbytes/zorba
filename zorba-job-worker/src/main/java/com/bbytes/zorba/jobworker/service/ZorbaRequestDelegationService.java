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
package com.bbytes.zorba.jobworker.service;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.exception.ProcessingException;

/**
 * A service interface for handling the processing of a zorba request.
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
public interface ZorbaRequestDelegationService {

	/**
	 * Process the {@link ZorbaRequest} and gets the job information from the request.This method
	 * should in turn call actual job processing
	 * 
	 * @param request
	 * @throws ProcessingException
	 */
	void processZorbaRequest(ZorbaRequest request) throws ProcessingException;
	
	/**
	 * Checks if thead is available to handle the Priority message arrived
	 * @param priority
	 * @return
	 * @throws ProcessingException
	 */
	boolean isThreadAvailable(Priority priority) throws ProcessingException;
}
