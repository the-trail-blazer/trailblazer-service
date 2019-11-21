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


/**
 * Rest controller all things related to queries of {@link Trail}. Full authentication is required
 * for certain requests.
 */
@RestController
@RequestMapping("/trails")
@ExposesResourceFor(Trail.class)
public class TrailController {

  private final TrailRepository trailRepository;
  private final UserRepository userRepository;


  /**
   * Public constructor.
   *
   * @param trailRepository
   * @param userRepository
   */
  @Autowired
  public TrailController(TrailRepository trailRepository, UserRepository userRepository) {
    this.trailRepository = trailRepository;
    this.userRepository = userRepository;
  }


  /**
   * Gets a trail by ID
   * @param id of the corresponding trail.
   * @param authentication {@link Authentication} token received from Google.
   * @return A {@link Trail} the authenticated user is available.
   */
  @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Trail get(@PathVariable("id") Long id, Authentication authentication) {
    return trailRepository.getTrailByCreatorAndId((User) authentication.getPrincipal(), id).get();
  }

  /**
   * Gets a trail's geometries, requires identification.
   * @param id corresponding to a trail.
   * @param authentication Google authentication token.
   * @return {@link Geometry} a GeoJson object.
   */
  @GetMapping(path = "{id}/geometry", produces = MediaType.APPLICATION_JSON_VALUE)
  public Geometry getGeometry(@PathVariable("id") Long id, Authentication authentication) {
    return trailRepository.getTrailByCreatorAndId((User) authentication.getPrincipal(), id).get()
        .getGeometry();
  }

  /**
   * Get requests mapped by search. Rest endpoint is "/trails/search?public={@link boolean}&name={trail name containing}
   * @param isPublic {@link boolean} value, default is true.
   * @param name {@link String} search parameter indicating a portion of the trail name being searched for.
   * @param authentication {@link Authentication} google athentication token.
   * @return {@link Iterable<Trail>} A list of trails matching the search queries by the user.
   */
  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<Trail>> get(
      @RequestParam(value = "public", defaultValue = "true", required = false) boolean isPublic,
      @RequestParam("name") String name,
      Authentication authentication) {
    Iterable<Trail> trails;
    if (isPublic) {
      trails = trailRepository
          .getAllByTrailPublicIsTrueAndNameContains(name);
    } else {
      trails = trailRepository
          .getAllByCreatorAndTrailPublicIsTrueAndNameContains(
              ((User) authentication.getPrincipal()), name);
    }
    return ResponseEntity.accepted().body(trails);
  }


  /**
   * Get request for the users' created trails
   * @param authentication token from google
   * @return {@link ResponseEntity<Iterable<Trail>>} of {@link User}'s trails.
   */
  @GetMapping(value = "/mytrails", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<Trail>> get(Authentication authentication) {
    Iterable<Trail> trails = trailRepository.getAllByCreatorOrderByIdAsc(
        (User) authentication.getPrincipal());
    return ResponseEntity.accepted().body(trails);
  }

  /**
   * Adds a trail to the database via post request.
   * @param trail request body contains a {@link Trail} requires a unique trail name to the user.
   * @param auth {@link Authentication} Google authentication token.
   * @return {@link ResponseEntity<Trail>} containing the server's response.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Trail> post(@RequestBody Trail trail, Authentication auth) {
    trail.setCreator((User) auth.getPrincipal());
    trailRepository.save(trail);
    return ResponseEntity.accepted().body(trail);
  }


  /**
   * Get request for all the {@link Trail} available to the {@link User}
   * @param authentication {@link Authentication} A google authentication token.
   * @return {@link Iterable<Trail>} All Trails available to the {@link User}.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Trail> getAllAuthenticated(Authentication authentication) {
    return trailRepository.getAllByPublicOrUser(((User) authentication.getPrincipal()).getId());
  }

  /**
   * Gets all public {@link Trail}
   * @return {@link Iterable<Trail>} public trails.
   */
  @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Trail> getAll() {
    return trailRepository.getAllByTrailPublicIsTrue();
  }

  /**
   * Handles the database responses when queries are not found.
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }


}
