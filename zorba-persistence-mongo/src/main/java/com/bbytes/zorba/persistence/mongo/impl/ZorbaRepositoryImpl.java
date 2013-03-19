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
package com.bbytes.zorba.persistence.mongo.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import com.bbytes.zorba.domain.Entity;
import com.bbytes.zorba.persistence.IZorbaDao;
import com.bbytes.zorba.persistence.exception.PersistenceException;
import com.mongodb.DBObject;

/**
 * Base {@link IZorbaDao} implementation for CRUD operations
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 * @since 0.0.1
 * 
 */
public class ZorbaRepositoryImpl<T extends Entity> implements IZorbaDao<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;

	@Override
	public T create(T entity) throws PersistenceException {
		return save(entity);
	}

	@Override
	public T save(T entity) throws PersistenceException {
		if (entity != null)
			mongoTemplate.save(entity);
		return entity;
	}

	@Override
	public T update(T entity) throws PersistenceException {
		if (entity != null) {
			DBObject dbo = (DBObject) mongoTemplate.getConverter()
					.convertToMongoType(entity);
			mongoTemplate.updateFirst(query(where("_id").is(entity.getId())),
					Update.fromDBObject(dbo), Entity.class);
		}
		return entity;
	}

	@Override
	public void create(List<T> entityList) throws PersistenceException {
		if (entityList != null) {
			for (T entity : entityList) {
				save(entity);
			}
		}
	}

	@Override
	public List<T> update(List<T> entityList) throws PersistenceException {
		if (entityList != null) {
			for (T entity : entityList) {
				update(entity);
			}
		}
		return entityList;
	}

	@Override
	public void remove(T entity) throws PersistenceException {
		if (entity != null)
			mongoTemplate.remove(entity);
	}

	@Override
	public void remove(List<T> listOfEntities) throws PersistenceException {

		if (listOfEntities != null) {
			for (T entity : listOfEntities) {
				remove(entity);
			}
		}
	}

	@Override
	public void deleteById(String entityId) throws PersistenceException {
		mongoTemplate.remove(query(where("_id").is(entityId)), Entity.class);
	}

	@Override
	public void delete(T entity) throws PersistenceException {
		remove(entity);
	}

}
