package io.trailblazer.trailblazerservice.controller;


import io.trailblazer.trailblazerservice.model.dao.TrailRepository;
import io.trailblazer.trailblazerservice.model.dao.UserRepository;
import io.trailblazer.trailblazerservice.model.entity.Trail;
import io.trailblazer.trailblazerservice.model.entity.User;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trails")
@ExposesResourceFor(Trail.class)
public class TrailController {

  private final TrailRepository trailRepository;
  private final UserRepository userRepository;

  @Autowired
  public TrailController(TrailRepository trailRepository, UserRepository userRepository) {
    this.trailRepository = trailRepository;
    this.userRepository = userRepository;
  }


  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Trail get(@PathVariable("id") Long id, Authentication authentication) {

    return trailRepository.getTrailByCreatorAndId((User) authentication.getPrincipal(), id).get();
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Trail> get(@RequestParam("name") String name,
      Authentication authentication) {
    Trail trail = trailRepository
        .getTrailByCreatorAndNameContains(((User) authentication.getPrincipal()), name).get();
    return ResponseEntity.accepted().body(trail);
  }


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Trail> post(@RequestBody Trail trail, Authentication auth) {

    trail.setCreator((User) auth.getPrincipal());
    trailRepository.save(trail);
    return ResponseEntity.accepted().body(trail);
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Trail> getAll(Authentication authentication) {
    return trailRepository.getAllByPublicOrUser(((User) authentication.getPrincipal()).getId());
  }


  @DeleteMapping(value = "{id:^\\d+$}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id, Authentication auth) {
    trailRepository.delete(get(id, auth));
  }


  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }


}
