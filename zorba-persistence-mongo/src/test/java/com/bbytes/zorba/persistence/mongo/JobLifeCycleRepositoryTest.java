package com.bbytes.zorba.persistence.mongo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.testing.ZorbaBaseTesting;
import com.bbytes.zorba.persistence.JobLifeCycleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/zorba-persistence-mongo-test-context.xml" })
public class JobLifeCycleRepositoryTest extends ZorbaBaseTesting{
	
	@Autowired
	JobLifeCycleDao jobLifeCycleDao;

	@Before
	public void setUp() throws Exception {
		insertJobLifeCycleObjects();
	}

	@After
	public void tearDown() throws Exception {
		jobLifeCycleDao.deleteAll();
	}

	@Test
	public void testGetPriorityQueue() {
		assertNotNull(jobLifeCycleDao.findOne("1"));
		assertNotNull(jobLifeCycleDao.findOne("2"));
		assertNull(jobLifeCycleDao.findOne("3"));
	}

}
