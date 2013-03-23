/**
 * 
 */
package com.bbytes.zorba.persistence;

import org.springframework.stereotype.Repository;

import com.bbytes.zorba.domain.IJob;

/**
 * Interface to access the jobs from the database
 * 
 * @author Dhanush Gopinath
 *
 */
@Repository
public interface JobDao extends ZorbaDao<IJob,String>{

}
