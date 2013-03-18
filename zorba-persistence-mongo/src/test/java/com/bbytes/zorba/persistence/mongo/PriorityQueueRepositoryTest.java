package com.bbytes.zorba.persistence.mongo;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/zorba-persistence-mongo-test-context.xml" })
public class PriorityQueueRepositoryTest extends ZorbaBaseTesting{
	
	@Autowired
	PriorityQueueRepository priorityQueueRepo;

	@Before
	public void setUp() throws Exception {
		insertPriorityQueues();
	}

	@After
	public void tearDown() throws Exception {
		priorityQueueRepo.deleteAll();
	}

	@Test
	public void testGetPriorityQueue() {
		assertNotNull(priorityQueueRepo.getPriorityQueue(Priority.CRITICAL));
		assertNotNull(priorityQueueRepo.getPriorityQueue(Priority.LOW));
	}

}
