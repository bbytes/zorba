package com.bbytes.zorba.messaging.rabbitmq;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.messaging.IQueueAdminService;
import com.bbytes.zorba.messaging.rabbitmq.impl.RabbitMQSender;

/**
 * Test class for {@link RabbitMQSender}
 * 
 * @author Dhanush Gopinath
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:/spring/zorba-messaging-test-context.xml" })
public class RabbitMQAdminServiceTest {

	@Autowired
	IQueueAdminService queueAdminService;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendZorbaRequestPriority() {
		boolean success = queueAdminService.createQueue("very.odd.name%$#@@");
		Assert.assertTrue(success);
		boolean deleteSuccess = queueAdminService.deleteQueue("very.odd.name%$#@@");
		Assert.assertTrue(deleteSuccess);
	}

	

}
