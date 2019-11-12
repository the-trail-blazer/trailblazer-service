package io.trailblazer.trailblazerservice.model.dao;

import io.trailblazer.trailblazerservice.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {

  Optional<User> getUserByOauthKey(String oauthKey);

  Optional<User> getUserById(UUID id);

}
