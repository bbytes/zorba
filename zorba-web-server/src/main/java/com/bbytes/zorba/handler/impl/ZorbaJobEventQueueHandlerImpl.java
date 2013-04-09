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

import org.springframework.beans.factory.annotation.Autowired;

import com.bbytes.zorba.domain.IJob;
import com.bbytes.zorba.domain.JobEvent;
import com.bbytes.zorba.domain.JobLifeCycle;
import com.bbytes.zorba.handler.ZorbaJobEventQueueHandler;
import com.bbytes.zorba.persistence.JobLifeCycleDao;

/**
 * 
 * Handles all {@link JobEvent} from job event queue and stores it in persistence layer. In this case the
 * {@link IJob} status is pulled from the even queue and converted into {@link JobLifeCycle} object and stored
 * Web layer can come up with job analytics using these life cycle object 
 * 
 * @author Thanneer
 * 
 * @version 0.0.1
 */
public class ZorbaJobEventQueueHandlerImpl implements ZorbaJobEventQueueHandler {

	@Autowired(required=true)
	private JobLifeCycleDao jobLifeCycleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bbytes.zorba.handler.ZorbaJobEventQueueHandler#handleZorbaJobEvent(com.bbytes.zorba.domain
	 * .JobEvent)
	 */
	@Override
	public void handleZorbaJobEvent(JobEvent event) throws Exception {
		JobLifeCycle jobLifeCycle = jobLifeCycleDao.findOne(event.getJobExecutionId());

		switch (event.getJobStatus()) {
		case STARTED:
			jobLifeCycle = JobLifeCyclePersistenceUtil.onJobStartedEvent(event);
			break;
		case COMPLETED:
			jobLifeCycle = JobLifeCyclePersistenceUtil.onJobCompletedEvent(event, jobLifeCycle);
			break;
		case INTERRUPTED:
			jobLifeCycle = JobLifeCyclePersistenceUtil.onJobInterruptedEvent(event, jobLifeCycle);
			break;
		case WAITING:
			jobLifeCycle = JobLifeCyclePersistenceUtil.onJobWaitingEvent(event, jobLifeCycle);
			break;
		case FAILED:
			jobLifeCycle = JobLifeCyclePersistenceUtil.onJobFailedEvent(event, jobLifeCycle);
			break;
		case RUNNNING:
			jobLifeCycle = JobLifeCyclePersistenceUtil.onJobRunningEvent(event, jobLifeCycle);
			break;
		default:
			jobLifeCycle = JobLifeCyclePersistenceUtil.onJobCompletedEvent(event, jobLifeCycle);
			break;
		}

		// save the life cycle object
		jobLifeCycleDao.save(jobLifeCycle);

	}

}
