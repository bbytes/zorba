package com.bbytes.zorba.messaging.rabbitmq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.bbytes.zorba.messaging.IQueueAdminService;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.impl.RabbitMQSender;
import com.bbytes.zorba.messaging.rabbitmq.listener.impl.PriorityQueueSynchRequestHandlerImpl;

/**
 * An unit test that will send requests to the priority queues, which will be then processed by
 * {@link PriorityQueueSynchRequestHandlerImpl} To run this test you need
 * {@link RequestHandlerServerTests#test()} to be running. This tests will not be run
 * when doing an mvn test
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/zorba-rabbitmq-client-test-context.xml" })
public class PriorityQueueRequestHandlerClientTests extends ZorbaBaseTesting {


	@Autowired
	RabbitMQSender sender;

	@Autowired
	IQueueAdminService qAdminService;

	@Before
	public void setUp() throws Exception {
		// clear the queues before running the test
		qAdminService.deleteQueueContent(Priority.CRITICAL.getQueueName());
		qAdminService.deleteQueueContent(Priority.CRITICAL.getQueueName() + ".reply");
		
		qAdminService.deleteQueueContent(Priority.MEDIUM.getQueueName());
		qAdminService.deleteQueueContent(Priority.MEDIUM.getQueueName() + ".reply");
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testSendZorbaRequestForSendMailJob() throws MessagingException, InterruptedException {
		ZorbaRequest request = createZorbaRequestForSendMailJob(Priority.MEDIUM);
		sender.send(request, Priority.MEDIUM);
		assertResponse(request);
	}

	@Test
	public void testSendZorbaRequestWithCustomObjectForSendMailJob() throws MessagingException, InterruptedException {
		ZorbaRequest request = createZorbaRequestForSendMailJobWithBasicPrimitiveArraysAndAddress(Priority.CRITICAL);
		sender.send(request, Priority.CRITICAL);
		assertResponse(request);
	}

	@Test
	public void testSendZorbaRequestWithCustomObjectForSendMailJob2() throws MessagingException, InterruptedException {
		ZorbaRequest request = createZorbaRequestForSendMailJobWithAllArraysAndAddress(Priority.CRITICAL);
		sender.send(request, Priority.CRITICAL);
		assertResponse(request);
	}

	/**
	 * Tests the complete request response route which includes processing the job and getting the
	 * result
	 * 
	 * @throws MessagingException
	 * @throws InterruptedException
	 */
	@Test
	public void testSendMailRoute() throws MessagingException, InterruptedException {
		ZorbaRequest request = createZorbaRequestForSendMailJobWithAllArraysAndAddress(Priority.CRITICAL);
		sender.send(request, Priority.CRITICAL);
		assertResponse(request);
	}

	protected void assertResponse(ZorbaRequest request) throws MessagingException, InterruptedException {
		// sleep for 30 sec so that the mail is actually sent and response is available in the reply
		// queue
		Thread.sleep(30000);
		String queueName = request.getPriority().getQueueName() + ".reply";
		ZorbaResponse response = sender.receiveResponse(queueName);
		assertNotNull(response);
		assertEquals(request.getId(), response.getId());
		assertEquals("MAIL SENT", response.getResult().get("RESPONSE"));
	}

}
