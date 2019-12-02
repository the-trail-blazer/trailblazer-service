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

  public Long getId() {
    return id;
  }

  public User getCreator() {
    return creator;
  }

  public Trail getTrail() {
    return trail;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getText() {
    return text;
  }

  public String getAction() {
    return action;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public void setTrail(Trail trail) {
    this.trail = trail;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setAction(String action) {
    this.action = action;
  }
}
