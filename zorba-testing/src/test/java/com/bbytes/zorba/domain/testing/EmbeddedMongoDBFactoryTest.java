package com.bbytes.zorba.domain.testing;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath*:spring/zorba-testing-context.xml" })
public class EmbeddedMongoDBFactoryTest extends ZorbaBaseTesting{
	
	@Autowired
	IEmbeddedMongoDBFactory embeddedMongoDBFactory;
	
	@Test
	public void testGetDB() {
		DB db = embeddedMongoDBFactory.getDb();
		assertNotNull(db);
		assertEquals("zorbaTest", db.getName());
	}

}
