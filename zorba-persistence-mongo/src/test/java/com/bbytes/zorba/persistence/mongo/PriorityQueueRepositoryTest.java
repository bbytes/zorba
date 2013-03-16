package com.bbytes.zorba.persistence.mongo;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.Priority;
import com.bbytes.zorba.domain.mongo.PriorityQueue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/zorba-persistence-mongo-test-context.xml" })
public class PriorityQueueRepositoryTest {
	
	@Autowired
	PriorityQueueRepository priorityQueueRepo;

	@Before
	public void setUp() throws Exception {
		PriorityQueue pq = new PriorityQueue();
		pq.setPriority(Priority.LOW);
		pq.setQueueName("low q");
		priorityQueueRepo.save(pq);
	}

	@After
	public void tearDown() throws Exception {
		priorityQueueRepo.deleteAll();
	}

	@Test
	public void testGetPriorityQueue() {
		assertNull(priorityQueueRepo.getPriorityQueue(Priority.CRITICAL));
		assertNotNull(priorityQueueRepo.getPriorityQueue(Priority.LOW));
	}

}
