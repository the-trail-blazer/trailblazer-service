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

public interface TrailRepository extends CrudRepository<Trail, Long> {

  Iterable<Trail> getAllByCreatorOrderByIdAsc(User user);

  Optional<Trail> getTrailByCreatorAndId(User user, Long key);

  Optional<Trail> getTrailByIdAndTrailPublicIsTrue(Long key);

  Iterable<Trail> getAllByCreatorAndTrailPublicIsTrueAndNameContains(User user, String name);

  Iterable<Trail> getAllByTrailPublicIsTrueAndNameContains(String name);

  Optional<Trail> findById(Long id);

  Iterable<Trail> getAllByTrailPublicIsTrue();

  Iterable<Trail> getTrailByCreatorOrTrailPublicIsTrue(User user);

  @SuppressWarnings("SqlNoDataSourceInspection")
  @Query(value = "SELECT * FROM trail t LEFT JOIN authenticated_user u ON t.creator_id = u.user_id WHERE t.trail_public OR u.user_id = :userId ORDER BY t.trail_name", nativeQuery = true)
  Iterable<Trail> getAllByPublicOrUser(UUID userId);


}
