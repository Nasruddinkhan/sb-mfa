/**
 * 23-Aug-2020
 * UserRepository.java 
 * ZAID
 */
package com.mypractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mypractice.model.User;

/**
 * @author ZAID
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * @param name
	 * @return
	 */
	User findByEmail(String name);

}
