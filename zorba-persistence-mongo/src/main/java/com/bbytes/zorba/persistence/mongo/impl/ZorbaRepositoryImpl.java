/**
 * 
 */
package com.bbytes.zorba.persistence.mongo.impl;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import com.bbytes.zorba.domain.IEntity;
import com.bbytes.zorba.domain.mongo.Entity;
import com.bbytes.zorba.persistence.IZorbaDao;
import com.bbytes.zorba.persistence.exception.PersistenceException;
import com.mongodb.DBObject;

/**
 * @author dhanush
 * 
 */
public class ZorbaRepositoryImpl<T extends IEntity> implements IZorbaDao<T> {

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
