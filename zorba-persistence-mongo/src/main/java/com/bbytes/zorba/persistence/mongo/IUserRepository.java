/**
 * 
 */
package com.bbytes.zorba.persistence.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bbytes.zorba.domain.IUser;
import com.bbytes.zorba.domain.mongo.User;


/**
 * An interface for accessing {@link User} objects. This interface is an
 * extension of {@link PagingAndSortingRepository}
 * 
 * @author Dhanush Gopinath
 * 
 */
public interface IUserRepository extends
		PagingAndSortingRepository<User, String> {

	/**
	 * Returns objects for given username.
	 * 
	 * @param msgId
	 * @param pageable
	 * @return
	 */
	public IUser findByUserName(String userName);

	/**
	 * Returns objects for given email.
	 * 
	 * @param msgId
	 * @param pageable
	 * @return
	 */
	public IUser findByEmail(String email);

	/**
	 * Returns objects for given email or username.
	 * 
	 * @param email
	 * @param userName
	 * @return
	 */
	public IUser findByEmailOrUserName(String email, String userName);

	/**
	 * Return User list by Client Id
	 * 
	 * @param userEmail
	 * @param pageable
	 * @return
	 */
	public Page<User> findUserByClientId(String clientId, Pageable pageable);

	/**
	 * Return User list by Role type
	 * 
	 * @param userEmail
	 * @param pageable
	 * @return
	 */
	@Query(value = "{ 'user.roleType' : ?0 }")
	public Page<User> findUserByRole(String roleType, Pageable pageable);

}