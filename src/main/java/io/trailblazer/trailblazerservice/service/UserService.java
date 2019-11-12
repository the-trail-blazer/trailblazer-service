package io.trailblazer.trailblazerservice.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import io.trailblazer.trailblazerservice.model.dao.UserRepository;
import io.trailblazer.trailblazerservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;

  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User getOrCreateUser(Payload payload) {
    String oauthKey = payload.getSubject();
    return repository.getUserByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauthKey(oauthKey);
          user.setEmail(payload.getEmail());
          return repository.save(user);
        });
  }


}
