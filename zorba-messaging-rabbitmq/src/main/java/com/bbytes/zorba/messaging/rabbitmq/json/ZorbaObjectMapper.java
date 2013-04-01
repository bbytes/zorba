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

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import com.bbytes.zorba.jobworker.domain.ZorbaData;
import com.bbytes.zorba.jobworker.domain.ZorbaRequest;
import com.bbytes.zorba.jobworker.domain.ZorbaResponse;

/**
 * The {@link ZorbaObjectMapper} extends {@link ObjectMapper} to provide its own serializer and
 * deserializer for the {@link ZorbaData} object that is passed along with {@link ZorbaRequest} and
 * {@link ZorbaResponse}
 * 
 * @see ZorbaDataDeserializer
 * @see ZorbaDataSerializer
 * 
 * @author Dhanush Gopinath
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public class ZorbaObjectMapper extends ObjectMapper {

	/**
	 * Default Constructor
	 */
	public ZorbaObjectMapper() {
		super();
		SimpleModule zorbaModule = new SimpleModule("zorba", new Version(0, 0, 1, "SNAPSHOT"));
		zorbaModule.addSerializer(new ZorbaDataSerializer(this));
		zorbaModule.addDeserializer(ZorbaData.class, new ZorbaDataDeserializer(this));
		registerModule(zorbaModule);
	}
}
