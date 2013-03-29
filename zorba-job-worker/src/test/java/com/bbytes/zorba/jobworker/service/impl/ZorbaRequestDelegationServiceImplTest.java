package com.bbytes.zorba.jobworker.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.testing.ZorbaBaseTesting;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.exception.ProcessingException;
import com.bbytes.zorba.jobworker.service.ZorbaRequestDelegationService;

/**
 * Unit test class to test {@link ZorbaRequestDelegationServiceImpl} 
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/zorba-job-worker-test-context.xml" })
public class ZorbaRequestDelegationServiceImplTest extends ZorbaBaseTesting {
	
	@Autowired
	ZorbaRequestDelegationService zorbaRequestDelegationService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessZorbaRequest() throws ProcessingException {
		ZorbaRequest request = createZorbaRequestForSendMailJob();
		zorbaRequestDelegationService.processZorbaRequest(request);
	}

	
	
	/**
	 * A TaskRejectedException will be thrown in this case because we are sending more requests than
	 * the TaskExecutor can handle
	 * 
	 * @throws ProcessingException
	 * 
	 */
	@Test(expected=TaskRejectedException.class)
	public void testProcessZorbaRequestLowPriority() throws ProcessingException {
		ZorbaRequest request = createZorbaRequestForSendMailJob(Priority.LOW);
		for(int i=0;i<20;i++) {
			zorbaRequestDelegationService.processZorbaRequest(request);
		}
	}

	@Test(expected=ProcessingException.class)
	public void testProcessNullZorbaRequest() throws ProcessingException {
		zorbaRequestDelegationService.processZorbaRequest(null);
	}
	
	@Test
	public void testIsThreadAvailable() throws ProcessingException {
		assertTrue(zorbaRequestDelegationService.isThreadAvailable(Priority.LOW));
	}

}
