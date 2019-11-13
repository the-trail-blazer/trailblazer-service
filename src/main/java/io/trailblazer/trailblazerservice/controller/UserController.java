package io.trailblazer.trailblazerservice.controller;


import io.trailblazer.trailblazerservice.model.dao.UserRepository;
import io.trailblazer.trailblazerservice.model.entity.User;
import io.trailblazer.trailblazerservice.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@ExposesResourceFor(User.class)
public class UserController {

  private final UserRepository userRepository;
  private final UserService userService;

  @Autowired
  public UserController(UserRepository userRepository,
      UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }


  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public User updateUser(@RequestBody User user, Authentication authentication) {
    User userFromRepo = userRepository
        .getUserByOauthKey(((User) authentication.getPrincipal()).getOauthKey()).get();
    if (user.getUsername() != null) {
      userFromRepo.setUsername(user.getUsername());
      userRepository.save(userFromRepo);
    }
    return userFromRepo;

  }


  @GetMapping()
  public User get(Authentication authentication) {
    return userRepository.getUserByOauthKey(((User) authentication.getPrincipal()).getOauthKey())
        .get();
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(ConstraintViolationException.class)
  void invalidUsername() {

  }
}
