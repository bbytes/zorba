/**
 * 
 */
package com.bbytes.zorba.persistence;

import java.util.List;

import com.bbytes.zorba.persistence.exception.PersistenceException;

/**
 * The base dao interface that will encapsulate the common operations
 * 
 * @author Dhanush Gopinath
 *
 */
public interface IZorbaDao<T> {
	/**
	 * Create an entity in the backend persitence system and return the saved
	 * object
	 * 
	 * @param entity
	 *            the object to be mapped saved
	 * @return the newly created Object
	 */
	public T create(T entity) throws PersistenceException;

	public T save(T entity) throws PersistenceException;

	/**
	 * Updates the entity to backend persistence layer.
	 * 
	 * @param entity
	 *            the object to be mapped to a JCR node
	 * @return the name of the  Node that was updated
	 */
	public T update(T entity) throws PersistenceException;

	public void create(List<T> entityList) throws PersistenceException;

	public List<T> update(List<T> entityList) throws PersistenceException;

	/**
	 * Permanently remove the entity with the path supplied (from a @JcrPath
	 * field).
	 * 
	 * @param path
	 *            the full path of the entity
	 */
	public void remove(T entity) throws PersistenceException;

	/**
	 * Permanently remove the entities
	 */
	public void remove(List<T> listOfEntities) throws PersistenceException;

	public void deleteById(final String entityId) throws PersistenceException;

	public void delete(final T entity) throws PersistenceException;
}
