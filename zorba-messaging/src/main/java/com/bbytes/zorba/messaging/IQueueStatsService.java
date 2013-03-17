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
package com.bbytes.zorba.messaging;

import java.util.List;

/**
 * The stats of the queue systems
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
public interface IQueueStatsService {

	/**
	 * Returns the queue message size given the queue name
	 * 
	 * @param queueName
	 * @return
	 */
	public long getQueueMessageSize(String queueName);

	/**
	 * Returns the entire queue size in the queue system
	 * 
	 * @return
	 */
	public long getTotalQueueSize();

	/**
	 * Returns the pending request size in the queue system
	 * 
	 * @return
	 */
	public long getPendingRequestSize();

	/**
	 * Get all queue name in the queue system
	 * 
	 * @return
	 */
	public List<String> getQueueNames();

	/**
	 * Returns the queue system name like rabbitmq , active mq , redis etc
	 * 
	 * @return
	 */
	public String getQueueSystemType();

}
