package com.bbytes.zorba.domain.mongo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bbytes.zorba.domain.IEntity;

/**
 * Default MongoDB Object implementation of {@link IEntity}
 * 
 * @author Dhanush Gopinath
 * @version 0.0.1
 * @since 0.0.1
 *
 */
@Document
@Data
public class Entity implements Serializable, IEntity {

	private static final long serialVersionUID = -5534745615089076782L;

	@Id
	private String id;

	private Date creationDate;

	private Date modificationDate;

	public void setId(String id) {
		this.id = id;
	}

	public String getId(String id) {
		return this.id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return this.modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;

	}
}
