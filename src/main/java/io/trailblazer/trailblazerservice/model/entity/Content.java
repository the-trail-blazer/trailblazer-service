/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

/**
 * The type of Content.
 */
@Entity
public class Content {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "content_id", updatable = false, nullable = false)
  private Long id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", updatable = false, nullable = false)
  private User creator;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "trail_id", updatable = false, nullable = false)
  private Trail trail;

  @JsonIgnore
  private String imageUrl;

  @Column
  private String text;

  private String action;

  /**
   * Gets Content by id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets creator.
   *
   * @return the creator
   */
  public User getCreator() {
    return creator;
  }

  /**
   * Gets trail.
   *
   * @return the trail
   */
  public Trail getTrail() {
    return trail;
  }

  /**
   * Gets image url.
   *
   * @return the image url
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Gets text.
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Gets action.
   *
   * @return the action
   */
  public String getAction() {
    return action;
  }

  /**
   * Sets creator.
   *
   * @param creator the creator
   */
  public void setCreator(User creator) {
    this.creator = creator;
  }

  /**
   * Sets trail.
   *
   * @param trail the trail
   */
  public void setTrail(Trail trail) {
    this.trail = trail;
  }

  /**
   * Sets image url.
   *
   * @param imageUrl the image url
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Sets text.
   *
   * @param text the text
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Sets action.
   *
   * @param action the action
   */
  public void setAction(String action) {
    this.action = action;
  }
}
