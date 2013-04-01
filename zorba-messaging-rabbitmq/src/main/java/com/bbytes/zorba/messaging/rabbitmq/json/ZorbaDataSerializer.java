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
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.MapSerializer;
import org.codehaus.jackson.map.ser.std.StdKeySerializers;
import org.codehaus.jackson.map.type.SimpleType;

import com.bbytes.zorba.jobworker.domain.ZorbaData;

/**
 * An implementation of {@link JsonSerializer} for {@link ZorbaData} class. This is based on the
 * {@link MapSerializer} class
 * 
 * @author Dhanush Gopinath
 * 
 * @version
 */
@SuppressWarnings("rawtypes")
public class ZorbaDataSerializer extends JsonSerializer<ZorbaData> {

	protected Class<ZorbaData> _handledType = ZorbaData.class;

	private ZorbaObjectMapper zorbaObjectMapper;

	/**
	 * @param zorbaObjectMapper
	 * @param T
	 */
	public ZorbaDataSerializer(ZorbaObjectMapper zorbaObjectMapper) {
		this.zorbaObjectMapper = zorbaObjectMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serialize(ZorbaData value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {

		jgen.writeStartObject();
		if (!value.isEmpty()) {
			serializeFields(value, jgen, provider);
		}
		jgen.writeEndObject();
	}

	private void serializeFields(ZorbaData<String, Serializable> value, JsonGenerator jgen, SerializerProvider provider)
			throws JsonProcessingException, IOException {
		final JsonSerializer<Object> keySerializer = StdKeySerializers.getStdKeySerializer(SimpleType
				.construct(String.class));
		for (Map.Entry<?, ?> entry : value.entrySet()) {
			Object valueElem = entry.getValue();
			// First, serialize key
			Object keyElem = entry.getKey();
			if (keyElem == null) {
				provider.getNullKeySerializer();
			} else {
				if (valueElem == null)
					continue;
				keySerializer.serialize(keyElem, jgen, provider);
			}
			// And then value
			if (valueElem == null) {
				provider.defaultSerializeNull(jgen);
			} else {
				Class<?> cc = valueElem.getClass();
				serializeValues(valueElem, cc, jgen, provider);
			}
		}

	}

	/**
	 * Serailizes the value Element to a JSON String
	 * 
	 * @param valueElem
	 * @param cc
	 * @param jgen
	 * @param provider
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	private void serializeValues(Object valueElem, Class<?> cc, JsonGenerator jgen, SerializerProvider provider)
			throws JsonGenerationException, IOException {
		if (valueElem == null)
			return;
		if (cc.equals(String.class)) {
			jgen.writeString(valueElem.toString());
		} else if (cc.equals(Integer.class)) {
			jgen.writeNumber(((Integer) valueElem).intValue());
		} else if (cc.equals(Boolean.class)) {
			jgen.writeBoolean(((Boolean) valueElem).booleanValue());
		} else if (cc.equals(Float.class)) {
			jgen.writeNumber(((Float) valueElem).floatValue());
		} else if (cc.equals(Double.class)) {
			jgen.writeNumber(((Double) valueElem).doubleValue());
		} else if (cc.equals(Short.class)) {
			jgen.writeNumber(((Short) valueElem).shortValue());
		} else if (cc.equals(Map.class)) {
			jgen.writeStartObject();
			// TODO : How to instantiate a MapSerialiser
			
			// JavaType javaType = provider.constructType(cc.getGenericSuperclass());
			// MapSerializer mapSer = MapSerializer.construct(null,
			// javaType, true, elementTypeSerializer, property,
			// keySerializer, elementValueSerializer);
			jgen.writeEndObject();
		} else if (cc.isArray()) {
			jgen.writeStartArray();
			Class<?> componentType = cc.getComponentType();
			int length = ((Object[]) valueElem).length;
			for (int i = 0; i < length; i++) {
				serializeValues(((Object[]) valueElem)[i], componentType, jgen, provider);
			}
			jgen.writeEndArray();
		}

		else if (cc instanceof Serializable) {
			/*
			 * This will take in the Serializer provided for the custom class by using
			 * @JsonSerialize annotation
			 */
			String json = zorbaObjectMapper.writeValueAsString(valueElem);
			jgen.writeRawValue(json);
			System.out.println(valueElem);
		}

	}

	@Override
	public Class<ZorbaData> handledType() {
		return _handledType;
	}
}
