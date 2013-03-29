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
package com.bbytes.zorba.jobworker.domain;

import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;

/**
 * 
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
public class ZorbaDataDeserializer extends JsonDeserializer<ZorbaData<String,Serializable>> {

	@Override
	public ZorbaData<String,Serializable> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		JsonToken t = jp.getCurrentToken();
		System.out.println(jp.getCurrentName());
		if (t == JsonToken.START_OBJECT) {
			t = jp.nextToken();
		}
		ZorbaData<String,Serializable> zorbaData = new ZorbaData<String,Serializable>();
		for (; t == JsonToken.FIELD_NAME; t = jp.nextToken()) {
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
				Object customObject = deserializeFromCustomObject(jp, ctxt);
			case FIELD_NAME:
			case END_OBJECT: // added to resolve [JACKSON-319], possible related issues
				break;

			}
		}
		return zorbaData;
	}

	private Object deserializeFromCustomObject(JsonParser jp, DeserializationContext ctxt) throws JsonParseException, IOException {
		JsonToken t = jp.getCurrentToken();
		System.out.println(jp.getCurrentName());
		if (t == JsonToken.START_OBJECT) {
			t = jp.nextToken();
		}
		
		for (; t == JsonToken.FIELD_NAME; t = jp.nextToken()) {
			String key = jp.getCurrentName();
			System.out.println(key);
			t = jp.nextToken();
			// and then others, generally requiring use of @JsonCreator
			switch (t) {
			case VALUE_STRING:

				String strValue = jp.getText();
				break;
			case VALUE_NUMBER_INT:
				int intValue = jp.getIntValue();
				break;
			case VALUE_NUMBER_FLOAT:
				float flValue = jp.getFloatValue();
				break;
			case VALUE_EMBEDDED_OBJECT:
				// return jp.getEmbeddedObject();
				System.out.println(jp.getEmbeddedObject().toString());
				break;
			case VALUE_TRUE:
			case VALUE_FALSE:
				boolean boolValue = jp.getBooleanValue();
				break;
			case START_ARRAY:
				// these only work if there's a (delegating) creator...
				// deserializeFromArray(jp, ctxt);
				break;
			case START_OBJECT:
				//handle objects inside the object
				Object customObject = deserializeFromCustomObject(jp, ctxt);
			case FIELD_NAME:
			case END_OBJECT: // added to resolve [JACKSON-319], possible related issues
				break;

			}
		}
		return null;
			
	}

	@Override
	public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
			throws IOException, JsonProcessingException {
		JsonToken t = jp.getCurrentToken();
		System.out.println(jp.getCurrentName());
		if (t == JsonToken.START_OBJECT) {
			t = jp.nextToken();
		}
		// TODO Auto-generated method stub
		return super.deserializeWithType(jp, ctxt, typeDeserializer);
	}
}
