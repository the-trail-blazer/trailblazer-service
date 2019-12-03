/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.controller;


import io.trailblazer.trailblazerservice.model.dao.UserCharacteristicsRepository;
import io.trailblazer.trailblazerservice.model.dao.UserRepository;
import io.trailblazer.trailblazerservice.model.entity.User;
import io.trailblazer.trailblazerservice.model.entity.UserCharacteristics;
import io.trailblazer.trailblazerservice.service.UserService;
import java.util.Date;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/user")
@ExposesResourceFor(User.class)
public class UserController {

  private final UserRepository userRepository;
  private final UserCharacteristicsRepository characteristicsRepository;
  private final UserService userService;

  /**
   * Instantiates a new User controller.
   *
   * @param userRepository            autowired the user repository
   * @param characteristicsRepository autowired the characteristics repository
   * @param userService               autowired the user service
   */
  @Autowired
  public UserController(UserRepository userRepository,
      UserCharacteristicsRepository characteristicsRepository,
      UserService userService) {
    this.userRepository = userRepository;
    this.characteristicsRepository = characteristicsRepository;
    this.userService = userService;
  }


  /**
   * Gets user.
   *
   * @param authentication the authentication
   * @return the user
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(Authentication authentication) {
    return userRepository.getUserByOauthKey(((User) authentication.getPrincipal()).getOauthKey())
        .get();
  }


  /**
   * Gets the user characteristics.
   *
   * @param authentication grants access to characteristics
   * @return returns characteristics of user
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public UserCharacteristics getCharacteristics(Authentication authentication) {
    User user = userRepository
        .getUserByOauthKey(((User) authentication.getPrincipal()).getOauthKey())
        .get();
    UserCharacteristics userCharacteristics = characteristicsRepository
        .getUserCharacteristicsByUser(user).get();
    return userCharacteristics;
  }


  /**
   * Update's the user characteristics.
   *
   * @param authentication      autowired the authentication
   * @param userCharacteristics autowired the characteristics
   * @return  returns the user characteristics
   */
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public UserCharacteristics updateUser(Authentication authentication,
      UserCharacteristics userCharacteristics) {
    User user = userRepository
        .getUserByOauthKey((((User) authentication.getPrincipal())).getOauthKey()).get();

    if (userCharacteristics.getUser() != null
        && userCharacteristics.getUser().getUsername() != null) {
      user.setUsername(userCharacteristics.getUser().getUsername());
      userRepository.save(user);
    }
    userCharacteristics.setUpdated(new Date());
    return characteristicsRepository.save(userCharacteristics);
  }


  /**
   * Returns invalid username response.
   */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(ConstraintViolationException.class)
  void invalidUsername() {

  }
}
