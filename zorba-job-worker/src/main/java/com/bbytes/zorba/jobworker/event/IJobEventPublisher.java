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
package com.bbytes.zorba.jobworker.event;

import com.bbytes.zorba.domain.JobEvent;

/**
 * The publish methods for job events are defined here.
 *
 * @author Thanneer
 *
 * @version 0.0.1
 */
public interface IJobEventPublisher {

	/**
	 * Publish the job event so that registered listeners can receive job events 
	 * @param jobEvent
	 */
	public void publish(JobEvent jobEvent);
}
