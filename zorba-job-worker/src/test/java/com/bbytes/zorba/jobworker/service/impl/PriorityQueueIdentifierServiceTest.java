package com.bbytes.zorba.jobworker.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.service.IPriorityQueueIdentifierService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/zorba-job-worker-test-context.xml" })
public class PriorityQueueIdentifierServiceTest {
	@Autowired
	IPriorityQueueIdentifierService priorityQueueIdentifierService;
	@Test
	public void testRegisterPriorityQueue() {
		assertNotNull(priorityQueueIdentifierService);
		priorityQueueIdentifierService.registerPriorityQueue(Priority.HIGH, "high q");
		assertNotNull(priorityQueueIdentifierService.getQueueName(Priority.HIGH));
	}

}
