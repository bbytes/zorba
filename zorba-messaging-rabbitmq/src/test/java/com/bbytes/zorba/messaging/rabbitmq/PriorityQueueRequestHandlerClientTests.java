package com.bbytes.zorba.messaging.rabbitmq;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.testing.ZorbaBaseTesting;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.impl.RabbitMQReceiver;
import com.bbytes.zorba.messaging.rabbitmq.impl.RabbitMQSender;
import com.bbytes.zorba.messaging.rabbitmq.listener.impl.PriorityQueueSynchRequestHandlerImpl;

/**
 * An unit test that will send requests to the priority queues, which will be then processed by
 * {@link PriorityQueueSynchRequestHandlerImpl} To run this test you need
 * {@link PriorityQueueRequestHandlerServerTests#test()} to be running. This tests will not be run
 * when doing an mvn test
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/zorba-messaging-test-context.xml" })
public class PriorityQueueRequestHandlerClientTests extends ZorbaBaseTesting {

	@Autowired
	ZorbaRequest zorbaRequest;

	@Autowired
	ZorbaResponse zorbaResponse;
	
	@Autowired
	RabbitMQSender sender;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendZorbaRequestPriorityBySynchRequestHandler() throws MessagingException, InterruptedException {
		Priority p = Priority.HIGH;
		String id = UUID.randomUUID().toString();
		zorbaRequest.setId(id);
		sender.send(zorbaRequest,p);
	}

	@Test
	public void testSendZorbaRequestForSendMailJob() throws MessagingException, InterruptedException {
		ZorbaRequest request = createZorbaRequestForSendMailJob(Priority.MEDIUM);
		sender.send(request, Priority.MEDIUM);
		//sleep for 10 sec so that the mail is actually sent
		Thread.sleep(10000);
	}
	
	@Test
	public void testSendZorbaRequestWithCustomObjectForSendMailJob() throws MessagingException, InterruptedException {
		ZorbaRequest request = createZorbaRequestForSendMailJobWithAddress(Priority.CRITICAL);
		sender.send(request, Priority.CRITICAL);
		//sleep for 10 sec so that the mail is actually sent
		Thread.sleep(10000);
	}

}
