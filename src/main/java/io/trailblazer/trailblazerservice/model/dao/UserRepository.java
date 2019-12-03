/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface for User repository.
 */
public interface UserRepository extends CrudRepository<User, UUID> {

  /**
   * Gets user by oauth key.
   *
   * @param oauthKey the oauth key
   * @return the user by oauth key
   */
  Optional<User> getUserByOauthKey(String oauthKey);

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   */
  Optional<User> getUserById(UUID id);

}
