/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import io.trailblazer.trailblazerservice.model.dao.UserCharacteristicsRepository;
import io.trailblazer.trailblazerservice.model.dao.UserRepository;
import io.trailblazer.trailblazerservice.model.entity.User;
import io.trailblazer.trailblazerservice.model.entity.UserCharacteristics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The User service.
 */
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserCharacteristicsRepository userCharacteristicsRepository;

  /**
   * Instantiates a new User service.
   *
   * @param repository                    the repository
   * @param userRepository                the user repository
   * @param userCharacteristicsRepository the user characteristics repository
   */
  @Autowired
  public UserService(UserRepository repository,
      UserRepository userRepository,
      UserCharacteristicsRepository userCharacteristicsRepository) {
    this.userRepository = userRepository;
    this.userCharacteristicsRepository = userCharacteristicsRepository;
  }

  /**
   * Gets or create's user.
   *
   * @param payload the payload
   * @return the or create user
   */
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
