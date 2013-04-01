/*
 * Copyright (C) 2013 The Zorba Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.bbytes.zorba.messaging.rabbitmq.json;

import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.bbytes.zorba.jobworker.domain.ZorbaData;

/**
 * 
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
public class ZorbaDataDeserializer extends JsonDeserializer<ZorbaData<String,Serializable>> {
	
	private ZorbaObjectMapper zorbaObjectMapper;
	
	/**
	 * @param zorbaObjectMapper
	 * @param T
	 */
	public ZorbaDataDeserializer(ZorbaObjectMapper zorbaObjectMapper) {
		this.zorbaObjectMapper = zorbaObjectMapper;
	}

	
	@Override
	public ZorbaData<String,Serializable> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		ZorbaData<String,Serializable> zorbaData = new ZorbaData<String,Serializable>();
		JsonToken t = jp.getCurrentToken();
		System.out.println(jp.getCurrentName());
		if (t == JsonToken.START_OBJECT) {
			t = jp.nextToken();
		}
		for (; t != JsonToken.END_OBJECT; t = jp.nextToken()) {
			String key = jp.getCurrentName();
			System.out.println(jp.getCurrentName());
			t = jp.nextToken();
			// and then others, generally requiring use of @JsonCreator
			switch (t) {
			case VALUE_STRING:
				String strValue = jp.getText();
				zorbaData.put(key, strValue);
				break;
			case VALUE_NUMBER_INT:
				int intValue = jp.getIntValue();
				zorbaData.put(key, intValue);
				break;
			case VALUE_NUMBER_FLOAT:
				float flValue = jp.getFloatValue();
				zorbaData.put(key, flValue);
				break;
			case VALUE_EMBEDDED_OBJECT:
				// return jp.getEmbeddedObject();
				System.out.println(jp.getEmbeddedObject().toString());
				break;
			case VALUE_TRUE:
			case VALUE_FALSE:
				boolean boolValue = jp.getBooleanValue();
				zorbaData.put(key, boolValue);
				break;
			case START_ARRAY:
				// these only work if there's a (delegating) creator...
				// deserializeFromArray(jp, ctxt);
				break;
			case START_OBJECT:
				//handle objects inside the object
				Object customObject = null;
				try {
					customObject = deserializeFromCustomObject(jp, ctxt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				zorbaData.put(key, (Serializable) customObject);
				break;
			case FIELD_NAME:
			case END_OBJECT: // added to resolve [JACKSON-319], possible related issues
				break;

			}
		}
		return zorbaData;
	}

	/**
	 * Deserializes the JSON object to the custom object after fetching the value of the property @class
	 * 
	 * @param jp
	 * @param ctxt
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Object deserializeFromCustomObject(JsonParser jp, DeserializationContext ctxt) throws JsonParseException, IOException, ClassNotFoundException {
		JsonToken t = jp.getCurrentToken();
		if (t == JsonToken.START_OBJECT) {
			t = jp.nextToken();
		}
		// The assumption is custom class will have @JsonTypeInfo defined with Property name as @class
/*		String classKey = jp.getCurrentName();
		if(classKey!="@class") {
			throw new JsonParseException("Custom Classes should declare a JsonTypeInfo as the first entry", jp.getCurrentLocation());
		}
		t = jp.nextToken();
		String className = jp.getText();
		Class<?> clazz = Class.forName(className);
		return zorbaObjectMapper.readValue(jp, CustomAddress.class);*/
		
		/*
		 * The code commented above should be the proper way of parsing the json. But, when we move
		 * to the next token to get the class name, the JsonParser object advances one level and the
		 * readValue method throws RuntimeException
		 * 
		 * The code given below first converts the parser into a JsonNode and then we fetch the
		 * class name and converts it to the value
		 */
		
		JsonNode jNode =  zorbaObjectMapper.readTree(jp);
		String className = jNode.get("@class").getTextValue();
		Class<?> clazz = Class.forName(className);
		return zorbaObjectMapper.treeToValue(jNode, clazz);
	}
}
