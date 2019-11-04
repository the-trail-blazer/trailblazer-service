package io.trailblazer.trailblazerservice.model.dao;


import io.trailblazer.trailblazerservice.model.entity.Trail;
import io.trailblazer.trailblazerservice.model.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TrailRepository extends CrudRepository<Trail, Long> {

  Iterable<Trail> getAllByCreatorOrderByIdAsc(User user);

  Optional<Trail> getTrailByCreatorAndId(Object user, Long key);

}
