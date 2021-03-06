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
package com.bbytes.zorba.messaging.rabbitmq.listener;

import com.bbytes.zorba.messaging.exception.MessagingException;

/**
 * The interface for handling Zorba Requests arriving in the queues synchronously, which is to poll
 * for a single Message at a time with a synchronous, blocking method call.
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.1
 */
public interface ZorbaSynchRequestHandler {

	/**
	 * Handle the messages from queues. This method has to be scheduled as a cron job
	 * 
	 * @throws MessagingException
	 */
	void handleZorbaRequests() throws MessagingException;
}
