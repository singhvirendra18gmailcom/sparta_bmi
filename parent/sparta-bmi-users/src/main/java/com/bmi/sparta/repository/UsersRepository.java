/**
 * 
 */
package com.bmi.sparta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author singh
 *
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

}
