/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.dao;


import io.trailblazer.trailblazerservice.model.entity.Trail;
import io.trailblazer.trailblazerservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * The interface Trail repository.
 */
public interface TrailRepository extends CrudRepository<Trail, Long> {

  /**
   * Gets all by creator order by id asc.
   *
   * @param user the user
   * @return all by creator order by id asc
   */
  Iterable<Trail> getAllByCreatorOrderByIdAsc(User user);

  /**
   * Gets trail by creator and id.
   *
   * @param user the user
   * @param key  the key
   * @return the trail by creator and id
   */
  Optional<Trail> getTrailByCreatorAndId(User user, Long key);

  /**
   * Gets trail by id and trail public is true.
   *
   * @param key the key
   * @return the trail by id and trail public is true
   */
  Optional<Trail> getTrailByIdAndTrailPublicIsTrue(Long key);

  /**
   * Gets all by creator and trail public is true and name contains.
   *
   * @param user the user
   * @param name the name
   * @return the all by creator and trail public is true and name contains
   */
  Iterable<Trail> getAllByCreatorAndTrailPublicIsTrueAndNameContains(User user, String name);

  /**
   * Gets all by trail public is true and name contains.
   *
   * @param name the name
   * @return the all by trail public is true and name contains
   */
  Iterable<Trail> getAllByTrailPublicIsTrueAndNameContains(String name);

  Optional<Trail> findById(Long id);

  /**
   * Gets all by trail public is true.
   *
   * @return the all by trail public is true
   */
  Iterable<Trail> getAllByTrailPublicIsTrue();

  /**
   * Gets trail by creator or trail public is true.
   *
   * @param user the user
   * @return the trail by creator or trail public is true
   */
  Iterable<Trail> getTrailByCreatorOrTrailPublicIsTrue(User user);

  /**
   * Gets all by public or user.
   *
   * @param userId the user id
   * @return the all by public or user
   */
  @SuppressWarnings("SqlNoDataSourceInspection")
  @Query(value = "SELECT * FROM trail t LEFT JOIN authenticated_user u ON t.creator_id = u.user_id WHERE t.trail_public OR u.user_id = :userId ORDER BY t.trail_name", nativeQuery = true)
  Iterable<Trail> getAllByPublicOrUser(UUID userId);


}
