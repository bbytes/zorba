package com.bbytes.zorba.messaging.rabbitmq;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.messaging.rabbitmq.impl.RabbitMQSender;

/**
 * Test class for {@link RabbitMQSender}
 * 
 * @author Dhanush Gopinath
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:/spring/youtube-module-app-context.xml" })
public class RabbitMQSenderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendZorbaRequestPriority() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendZorbaRequestString() {
		fail("Not yet implemented");
	}

	@Test
	public void testReceiveResponseString() {
		fail("Not yet implemented");
	}

	@Test
	public void testReceiveResponsePriority() {
		fail("Not yet implemented");
	}

}
