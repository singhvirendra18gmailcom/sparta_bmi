/**
 * 
 */
package com.bmi.sparta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



/**
 * @author singh
 *
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Integer>{

	
	

}
