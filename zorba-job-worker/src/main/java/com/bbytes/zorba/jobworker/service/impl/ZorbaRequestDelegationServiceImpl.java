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
package com.bbytes.zorba.jobworker.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.JobProcessor;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.exception.ProcessingException;
import com.bbytes.zorba.jobworker.service.ZorbaRequestDelegationService;


/**
 * 
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
@Service(value="zorbaRquestDelegationService")
public class ZorbaRequestDelegationServiceImpl implements ZorbaRequestDelegationService {

	private static final Logger log = Logger.getLogger(ZorbaRequestDelegationServiceImpl.class);
	
	@Resource(name = "threadPoolExecutors")
	private Map<Priority, TaskExecutor> taskThreadPools;
	
	@Autowired
	private JobProcessor jobProcessor;
	
	/* (non-Javadoc)
	 * @see com.bbytes.zorba.jobworker.service.ZorbaRequestProcessingService#processZorbaRequest(com.bbytes.zorba.jobworker.domain.ZorbaRequest)
	 */
	@Override
	public void processZorbaRequest(ZorbaRequest request) throws ProcessingException {
		if(request == null) {
			log.error("Null ZorbaRequest");
			throw new ProcessingException("Null ZorbaRequest");
		}
		jobProcessor.processJob(request.getJobName(), request.getData());
	}

	@Override
	public boolean isThreadAvailable(Priority priority) throws ProcessingException {
		TaskExecutor taskExecutor = taskThreadPools.get(priority);
		if (taskExecutor instanceof ThreadPoolTaskExecutor) {
			int maxPoolSize = ((ThreadPoolTaskExecutor) taskExecutor).getMaxPoolSize();
			int activeCnt = ((ThreadPoolTaskExecutor) taskExecutor).getActiveCount();
			int corePoolSize = ((ThreadPoolTaskExecutor) taskExecutor).getCorePoolSize();
			int poolSize = ((ThreadPoolTaskExecutor) taskExecutor).getPoolSize();
//			if (poolName.endsWith("realtime")) {
				log.debug("|  ------------------------------------------  |");
				log.debug("|      Priority Queue      ::: " + priority.getQueueName() + "        |");
				log.debug("|      Max Pool        ::: " + maxPoolSize + "        |");
				log.debug("|      Core pool size  ::: " + corePoolSize + "        |");
				log.debug("|      Pool size       ::: " + poolSize + "        |");
				log.debug("|      Active Count    ::: " + activeCnt + "        |");
				log.debug("|  ------------------------------------------  |");
				log.debug("  ");

//			}
			if (activeCnt < maxPoolSize && poolSize < maxPoolSize) {
				return true;
			} else {
				return false;
			}
		} else {
			log.error("Not Supported ");
			return false;
		}
	}
}
