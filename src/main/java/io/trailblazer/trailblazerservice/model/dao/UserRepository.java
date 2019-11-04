package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> getUserByOauthKey(String oauthKey);

}
