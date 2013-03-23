/**
 * 
 */
package com.bbytes.zorba.persistence;

import org.springframework.stereotype.Repository;

import com.bbytes.zorba.domain.JobLifeCycle;

/**
 * Interface to access the job lifecycle from the database
 * 
 * @author Dhanush Gopinath
 *
 */
@Repository
public interface JobLifeCycleDao extends ZorbaDao<JobLifeCycle,String> {

}
