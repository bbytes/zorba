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
package com.bbytes.zorba.handler.impl;

import com.bbytes.zorba.handler.ZorbaJobEventQueueErrorHandler;

/**
 * Logs all the job event queue related error 
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
public class ZorbaJobEventQueueErrorHandlerImpl implements ZorbaJobEventQueueErrorHandler {

	/* (non-Javadoc)
	 * @see org.springframework.util.ErrorHandler#handleError(java.lang.Throwable)
	 */
	@Override
	public void handleError(Throwable t) {
		// log error
		
	}


}
