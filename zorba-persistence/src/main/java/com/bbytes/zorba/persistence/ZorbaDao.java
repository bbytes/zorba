/**
 * 
 */
package com.bbytes.zorba.persistence;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * The base dao interface that will encapsulate the common operations
 * 
 * @author Dhanush Gopinath
 * 
 */
@Repository
public interface ZorbaDao<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	
	

}
