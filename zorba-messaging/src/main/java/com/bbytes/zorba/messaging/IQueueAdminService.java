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
package com.bbytes.zorba.messaging;

/**
 * 
 *
 * @author Thanneer
 *
 * @version 
 */
public interface IQueueAdminService {

	/**
	 * Creates a queue with given name and returns true if successful
	 * @param queueName
	 * @return
	 */
	public boolean createQueue(String queueName);
	
	/**
	 * Delete queue if it exist and returns true if successful
	 * @param queueName
	 * @return
	 */
	public boolean deleteQueue(String queueName);
	
	/**
	 * Delete all the pending queue messages
	 * @param queueName
	 * @return
	 */
	public boolean deleteQueueContent(String queueName);
}
