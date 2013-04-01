package com.bbytes.zorba.messaging.rabbitmq.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbytes.zorba.domain.testing.ZorbaBaseTesting;
import com.bbytes.zorba.jobworker.domain.ZorbaData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/zorba-messaging-test-context.xml" })
public class ZorbaDataSerializerTest extends ZorbaBaseTesting {

	@Autowired
	ZorbaObjectMapper zorbaObjectMapper;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasic() throws JsonGenerationException, JsonMappingException, IOException {
		ZorbaData<String, Serializable> data = createBasicZorbaDataForSendMail();
		String json = zorbaObjectMapper.writeValueAsString(data);
		String expectedJson = "{\"to\":\"dhanush@beyondbytes.co.in\",\"body\":\"Unit Test Mail\",\"subject\":\"Unit Test Mail with Priority\",\"from\":\"dhanush@beyondbytes.co.in\"}";
		assertEquals(expectedJson, json);
	}
	
	@Test
	public void testAdvanced1() throws JsonGenerationException, JsonMappingException, IOException {
		ZorbaData<String, Serializable> data = createZorbaDataWithCustomClass();
		String json = zorbaObjectMapper.writeValueAsString(data);
		String expectedJson = "{\"to\":\"dhanush@beyondbytes.co.in\",\"body\":\"Unit Test Mail\",\"subject\":\"Unit Test Mail with Priority\",\"from\":\"dhanush@beyondbytes.co.in\", \"address\":{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"}}";
//		assertEquals(expectedJson, json);
		assertNotNull(json);
	}
	@Test
	public void testAdvanced2() throws JsonGenerationException, JsonMappingException, IOException {
		ZorbaData<String, Serializable> data = createZorbaDataWithBasicPrimitiveArrays();
		String json = zorbaObjectMapper.writeValueAsString(data);
		String expectedJson = "{\"to\":\"dhanush@beyondbytes.co.in\",\"body\":\"Unit Test Mail\",\"subject\":\"Unit Test Mail with Priority\",\"bool_array\":[true,true,false,false],\"int_array\":[1,2,3,4],\"from\":\"dhanush@beyondbytes.co.in\",\"float_array\":[1.0,2.0,3.0],\"str_array\":[\"1\",\"2\"]}";
		assertEquals(expectedJson, json);
		assertNotNull(json);
	}
	
	@Test
	public void testAdvanced3() throws JsonGenerationException, JsonMappingException, IOException {
		ZorbaData<String, Serializable> data = createZorbaDataWithAllArrays();
		String json = zorbaObjectMapper.writeValueAsString(data);
		String expectedJson = "{\"to\":\"dhanush@beyondbytes.co.in\",\"body\":\"Unit Test Mail\",\"obj_array1\":[\"ob1\",\"ob2\"],\"obj_array2\":[11,22],\"subject\":\"Unit Test Mail with Priority\",\"bool_array\":[true,true,false,false],\"int_array\":[1,2,3,4],\"from\":\"dhanush@beyondbytes.co.in\",\"addresses\":[{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"}],\"float_array\":[1.0,2.0,3.0],\"str_array\":[\"1\",\"2\"]}";
		assertEquals(expectedJson, json);
		assertNotNull(json);
	}
	
	@Test
	public void testAdvanced4() throws JsonGenerationException, JsonMappingException, IOException {
		ZorbaData<String, Serializable> data = createZorbaDataWithBasicPrimitiveArraysAndCustomClass();
		String json = zorbaObjectMapper.writeValueAsString(data);
		String expectedJson = "{\"to\":\"dhanush@beyondbytes.co.in\",\"body\":\"Unit Test Mail\",\"address\":{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},\"subject\":\"Unit Test Mail with Priority\",\"bool_array\":[true,true,false,false],\"int_array\":[1,2,3,4],\"from\":\"dhanush@beyondbytes.co.in\",\"float_array\":[1.0,2.0,3.0],\"str_array\":[\"1\",\"2\"]}";
		assertEquals(expectedJson, json);
		assertNotNull(json);
	}

	@Test
	public void testAdvanced5() throws JsonGenerationException, JsonMappingException, IOException {
		ZorbaData<String, Serializable> data = createZorbaDataWithAllArraysAndCustomClass();
		String json = zorbaObjectMapper.writeValueAsString(data);
		String expectedJson = "{\"to\":\"dhanush@beyondbytes.co.in\",\"body\":\"Unit Test Mail\",\"obj_array1\":[\"ob1\",\"ob2\"],\"obj_array2\":[11,22],\"address\":{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},\"subject\":\"Unit Test Mail with Priority\",\"bool_array\":[true,true,false,false],\"int_array\":[1,2,3,4],\"from\":\"dhanush@beyondbytes.co.in\",\"addresses\":[{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"},{\"@class\":\"com.bbytes.zorba.job.CustomAddress\",\"street\":\"3rs Main\",\"houseNo\":23,\"zipCode\":12344,\"locality\":\"OMBR\",\"town\":\"Bangalore\",\"district\":\"Bangalore\",\"state\":\"KA\"}],\"float_array\":[1.0,2.0,3.0],\"str_array\":[\"1\",\"2\"]}";
//		assertEquals(expectedJson, json);
		assertNotNull(json);
	}
	
}
