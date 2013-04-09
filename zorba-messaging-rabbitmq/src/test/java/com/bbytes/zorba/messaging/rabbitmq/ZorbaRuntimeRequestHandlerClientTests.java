package com.bbytes.zorba.messaging.rabbitmq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.testing.ZorbaBaseTesting;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;
import com.bbytes.zorba.messaging.IQueueAdminService;
import com.bbytes.zorba.messaging.IQueueStatsService;
import com.bbytes.zorba.messaging.exception.MessagingException;
import com.bbytes.zorba.messaging.rabbitmq.impl.RabbitMQSender;
import com.bbytes.zorba.messaging.rabbitmq.listener.impl.PriorityQueueSynchRequestHandlerImpl;

/**
 * An unit test that will send requests to the run time queues, which will be then processed by
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
public class ZorbaRuntimeRequestHandlerClientTests extends ZorbaBaseTesting {

	@Autowired
	private RabbitMQSender sender;

	@Autowired
	private IQueueAdminService qAdminService;
	
	@Autowired
	private IQueueStatsService qStatsService;

	private String queueName = "runtime.test.queue";

	@Before
	public void setUp() throws Exception {
		qAdminService.createQueue(queueName);
	}

	@After
	public void tearDown() throws Exception {
		qAdminService.deleteQueue(queueName);
	}

	@Test
	public void testSendZorbaRequestPriorityBySynchRequestHandler() throws MessagingException, InterruptedException {
		Thread.sleep(2000);
		assertTrue(qStatsService.getQueueNames().contains(queueName));
		ZorbaRequest request = createZorbaRequestForSendMailJob();
		sender.send(request, queueName);
		assertResponse(request);
	}

	protected void assertResponse(ZorbaRequest request) throws MessagingException, InterruptedException {
		// sleep for 40 sec so that the mail is actually sent
		Thread.sleep(30000);
		String queueName = request.getPriority().getQueueName() + ".reply";
		ZorbaResponse response = sender.receiveResponse(queueName);
		assertNotNull(response);
		assertEquals(request.getId(), response.getId());
		assertEquals("MAIL SENT", response.getResult().get("RESPONSE"));
	}

}
