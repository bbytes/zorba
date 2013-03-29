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
package com.bbytes.zorba.jobworker.event.impl;

import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.jobworker.event.IJobEventListener;

/**
 * The default job event listener that publishes all the event to the event queue.The events form
 * the event queue is processed at web server level and stored in persistence layer like mongo db
 * 
 * @author Thanneer
 * 
 * @version
 */
public class JobEventListenerImpl implements IJobEventListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context
	 * .ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(JobEvent event) {
		System.out.println("event status:- " + event.getJobStatus());
		/*
		 * TODO this class needs to be moved to messaging and implement sending the response to
		 * jobevent queue and reply queue
		 */
	}

}
