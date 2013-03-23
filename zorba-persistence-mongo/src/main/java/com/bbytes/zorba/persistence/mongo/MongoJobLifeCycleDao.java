/**
 * 
 */
package com.bbytes.zorba.persistence.mongo;

import org.springframework.stereotype.Repository;

import com.bbytes.zorba.persistence.JobLifeCycleDao;

/**
 * Interface to access the job lifecycle from the database
 * 
 * @author Dhanush Gopinath
 *
 */
@Repository
public interface MongoJobLifeCycleDao extends JobLifeCycleDao {

}
