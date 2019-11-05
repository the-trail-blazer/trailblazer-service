package io.trailblazer.trailblazerservice.model.dao;


import io.trailblazer.trailblazerservice.model.entity.Trail;
import io.trailblazer.trailblazerservice.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TrailRepository extends CrudRepository<Trail, Long> {

  Iterable<Trail> getAllByCreatorOrderByIdAsc(User user);

  Optional<Trail> getTrailByCreatorAndId(Object user, Long key);

  Iterable<Trail> getAllByTrailPublicIsTrue();

  Iterable<Trail> getTrailByCreatorOrTrailPublicIsTrue(User user);


  @Query(value = "SELECT * FROM trail t LEFT JOIN authenticated_user u ON t.creator_id = u.user_id WHERE t.trail_public OR u.user_id = :userId ORDER BY t.name", nativeQuery = true)
  Iterable<Trail> getAllByPublicOrUser(Long userId);

}
