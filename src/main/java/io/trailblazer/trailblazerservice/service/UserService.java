package io.trailblazer.trailblazerservice.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import io.trailblazer.trailblazerservice.model.dao.UserCharacteristicsRepository;
import io.trailblazer.trailblazerservice.model.dao.UserRepository;
import io.trailblazer.trailblazerservice.model.entity.User;
import io.trailblazer.trailblazerservice.model.entity.UserCharacteristics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserCharacteristicsRepository userCharacteristicsRepository;

  @Autowired
  public UserService(UserRepository repository,
      UserRepository userRepository,
      UserCharacteristicsRepository userCharacteristicsRepository) {
    this.userRepository = userRepository;
    this.userCharacteristicsRepository = userCharacteristicsRepository;
  }

  public User getOrCreateUser(Payload payload) {
    String oauthKey = payload.getSubject();
    return userRepository.getUserByOauthKey(oauthKey)
        .orElseGet(() -> {

          User user = new User();
          user.setOauthKey(oauthKey);
          user.setEmail(payload.getEmail());
          user = userRepository.save(user);
          UserCharacteristics userCharacteristics = new UserCharacteristics();
          userCharacteristics.setUser(user);
          userCharacteristicsRepository.save(userCharacteristics);
          return user;
        });
  }


}
