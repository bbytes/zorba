package com.bbytes.zorba.jobworker.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.jobworker.exception.ProcessingException;
import com.bbytes.zorba.jobworker.service.ZorbaRequestDelegationService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/zorba-job-worker-test-context.xml" })
public class ZorbaRequestDelegationServiceImplTest {
	
	@Autowired
	ZorbaRequestDelegationService zorbaRequestDelegationService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessZorbaRequest() {
		
	}

	@Test
	public void testIsThreadAvailable() throws ProcessingException {
		assertTrue(zorbaRequestDelegationService.isThreadAvailable(Priority.LOW));
	}

}
