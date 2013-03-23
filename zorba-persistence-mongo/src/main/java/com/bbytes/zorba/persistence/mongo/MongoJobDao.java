/**
 * 
 */
package com.bbytes.zorba.persistence.mongo;

import org.springframework.stereotype.Repository;

import com.bbytes.zorba.persistence.JobDao;

/**
 * Interface to access the jobs from the database in mongo
 * 
 * @author Dhanush Gopinath
 *
 */
@Repository
public interface MongoJobDao extends JobDao{

}
