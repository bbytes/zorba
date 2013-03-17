/**
 * 
 */
package com.bbytes.zorba.domain;

/**
 * Interface definition for job to be registered in Zorba
 * 
 * @author Dhanush Gopinath
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public interface IJob extends IEntity {

	String getJobName();
	
	Class getJobClass();
	
	
}
