package com.oltoch.app.wss.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oltoch.app.wss.io.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	/** Finds an entity by the email address provided.
	 * It returns null if it doesn't exist
	 * @param  email must not be null
	 * @return returns UserEntity or null if no user found with the email
	 */
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByUserId(String userId);
}
