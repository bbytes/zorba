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
package com.bbytes.zorba.handler;

import com.bbytes.zorba.domain.JobEvent;

/**
 * An interface that handles the job event from event queue and stores it in persistence layer
 * 
 * @author Thanneer
 * 
 * @version
 */
public interface ZorbaJobEventQueueHandler {

	/**
	 * Handle the {@link JobEvent} and fire it to the listeners registered.
	 * 
	 * @param response
	 * @throws MessagingException
	 */
	public void handleZorbaJobEvent(JobEvent event) throws Exception;

}
