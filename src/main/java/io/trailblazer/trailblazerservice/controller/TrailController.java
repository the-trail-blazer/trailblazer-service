package io.trailblazer.trailblazerservice.controller;


import com.vividsolutions.jts.geom.Geometry;
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


  @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Trail get(@PathVariable("id") Long id, Authentication authentication) {
    return trailRepository.getTrailByCreatorAndId((User) authentication.getPrincipal(), id).get();
  }

  @GetMapping(path = "{id}/geometry", produces = MediaType.APPLICATION_JSON_VALUE)
  public Geometry getGeometry(@PathVariable("id") Long id, Authentication authentication) {
    return trailRepository.getTrailByCreatorAndId((User) authentication.getPrincipal(), id).get()
        .getGeometry();
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<Trail>> get(@RequestParam("name") String name,
      Authentication authentication) {
    Iterable<Trail> trail = trailRepository
        .getAllByCreatorAndNameContains(((User) authentication.getPrincipal()), name);
    return ResponseEntity.accepted().body(trail);
  }


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Trail> post(@RequestBody Trail trail, Authentication auth) {
    trail.setCreator((User) auth.getPrincipal());
    trailRepository.save(trail);
    return ResponseEntity.accepted().body(trail);
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Trail> getAllAuthenticated(Authentication authentication) {
    return trailRepository.getAllByPublicOrUser(((User) authentication.getPrincipal()).getId());
  }

  @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Trail> getAll() {
    return trailRepository.getAllByTrailPublicIsTrue();
  }



  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }


}
