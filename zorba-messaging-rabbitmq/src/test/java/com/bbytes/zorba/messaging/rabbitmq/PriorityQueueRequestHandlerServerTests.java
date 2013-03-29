package com.bbytes.zorba.messaging.rabbitmq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.messaging.rabbitmq.listener.impl.PriorityQueueSynchRequestHandlerImpl;

/**
 * An unit test to keep the {@link PriorityQueueSynchRequestHandlerImpl} running. The test method in
 * just starts the rabbit mq resources and makes the {@link PriorityQueueSynchRequestHandlerImpl}
 * wait for messages. Run this test before running {@link PriorityQueueRequestHandlerClientTests} - This tests will not be run when doing an mvn test
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/zorba-messaging-test-context.xml" })
public class PriorityQueueRequestHandlerServerTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(Integer.MAX_VALUE);
	}

}
