package com.bbytes.zorba.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Interface for the base entity
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 * 
 */
public interface IEntity extends Serializable {

	/**
	 * Sets the id
	 * 
	 * @param id
	 */
	void setId(String id);

	/**
	 * Returns the id
	 * 
	 * @param id
	 * @return
	 */
	String getId();

	/**
	 * Gets the date of creation of this entity
	 * 
	 * @return - Date of creation
	 */
	Date getCreationDate();

	/**
	 * Sets the date of creation of this entity
	 * 
	 * @param creationDate
	 *            - date to be set as creation date
	 */
	void setCreationDate(Date creationDate);

	/**
	 * Gets the date of modification of this entity
	 * 
	 * @return
	 */
	Date getModificationDate();

	/**
	 * Sets the date of modification of this entity
	 * 
	 * @param modificationDate
	 *            - date to be set as the date of modification of this entity
	 */
	void setModificationDate(Date modificationDate);
}