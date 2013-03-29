/*
 * Copyright (C) 2013 The Zorba Open Source Project 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.bbytes.zorba.jobworker.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.map.ser.std.MapSerializer;
import org.codehaus.jackson.map.ser.std.StdKeySerializers;
import org.codehaus.jackson.map.type.SimpleType;

/**
 * An implementation of {@link JsonSerializer} for {@link ZorbaData} class. This is based on the {@link MapSerializer} class
 *
 * @author Dhanush Gopinath
 *
 * @version 
 */
public class ZorbaDataSerializer extends JsonSerializer<ZorbaData<String,Serializable>> {
    
	/**
     * If value type can not be statically determined, mapping from
     * runtime value types to serializers are stored in this object.
     * 
     * @since 1.8
     */
    protected PropertySerializerMap _dynamicValueSerializers;
    
    
    public ZorbaDataSerializer() {
    	_dynamicValueSerializers = PropertySerializerMap.emptyMap();
    }

	@Override
	public void serialize(ZorbaData<String, Serializable> value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		
		jgen.writeStartObject();
        if (!value.isEmpty()) {
     
            serializeFields(value, jgen, provider);
        }        
        jgen.writeEndObject();
        
		// TODO Auto-generated method stub
		System.out.println(value);
	}

	private void serializeFields(ZorbaData<String, Serializable> value, JsonGenerator jgen, SerializerProvider provider) throws JsonProcessingException, IOException {
		final JsonSerializer<Object> keySerializer = StdKeySerializers.getStdKeySerializer(SimpleType.construct(String.class));
		PropertySerializerMap serializers = _dynamicValueSerializers;
		  for (Map.Entry<?,?> entry : value.entrySet()) {
	            Object valueElem = entry.getValue();
	            // First, serialize key
	            Object keyElem = entry.getKey();
	            if (keyElem == null) {
	                provider.getNullKeySerializer();
	            } else {
	                // [JACKSON-314] skip entries with null values?
	                if (valueElem == null) continue;
	                keySerializer.serialize(keyElem, jgen, provider);
	            }
	            // And then value
	            if (valueElem == null) {
	                provider.defaultSerializeNull(jgen);
	            } else {
	                Class<?> cc = valueElem.getClass();
	                serializeValues(valueElem, cc,jgen,provider);
	            }
		  }
		
	}

	private void serializeValues(Object valueElem, Class<?> cc, JsonGenerator jgen, SerializerProvider provider) throws JsonGenerationException, IOException {
		if (valueElem == null)
			return ;
		if (cc.equals(String.class)) {
			jgen.writeString(valueElem.toString());
		}
		else if (cc.equals(Integer.class)) {
			jgen.writeNumber(((Integer)valueElem).intValue());
		}
		else if (cc.equals(Boolean.class)) {
			jgen.writeBoolean(((Boolean)valueElem).booleanValue());
		}
		else if (cc.equals(Float.class)) {
			jgen.writeNumber(((Float)valueElem).floatValue());
		}
		else if (cc.equals(Double.class)) {
			jgen.writeNumber(((Double)valueElem).doubleValue());
		}
		else if (cc.equals(Short.class)) {
			jgen.writeNumber(((Short)valueElem).shortValue());
		}
		else if(cc.isArray()) {
			jgen.writeStartArray();
			Class<?> componentType = cc.getComponentType();
			int length = ((Object[])valueElem).length;
			for(int i=0; i<length; i++) {
				serializeValues(((Object[])valueElem)[i], componentType, jgen, provider);
			}
			jgen.writeEndArray();
		}
		else if(cc instanceof Serializable) {
			jgen.writeStartObject();
			jgen.writeFieldName("@class");
			jgen.writeString(valueElem.getClass().toString());
//			jgen.writeObject(valueElem);
			/*
			 * TODO : Get the Class fields and recursively will the objectsO
			 */
			jgen.writeEndObject();
			System.out.println(valueElem);
		}
		
	}
	
    /*
    /**********************************************************
    /* Internal methods
    /**********************************************************
     */
    
//    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map,
//            Class<?> type, SerializerProvider provider) throws JsonMappingException
//    {
//        PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSerializer(type, provider, _property);
//        // did we get a new map of serializers? If so, start using it
//        if (map != result.map) {
//            _dynamicValueSerializers = result.map;
//        }
//        return result.serializer;
//    }
//
//    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map,
//            JavaType type, SerializerProvider provider) throws JsonMappingException
//    {
//    	JsonSerializer<Object> serializer = provider.findValueSerializer(type, property);
//        new SerializerAndMapResult(serializer, newWith(type.getRawClass(), serializer));
//        
//        PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSerializer(type, provider, new BeanProperty());
//        if (map != result.map) {
//            _dynamicValueSerializers = result.map;
//        }
//        return result.serializer;
//    }


}
