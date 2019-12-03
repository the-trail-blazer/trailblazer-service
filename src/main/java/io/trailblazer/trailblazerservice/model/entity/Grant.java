/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * The type Grant.
 */
@Entity
@Table(name = "visibility_grant", indexes = {@Index(columnList = "created")}
)
public class Grant {

  @NonNull
  @Id
  @Column(name = "grant_id", nullable = false, unique = true)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "grantee_id", updatable = false)
  private User grantee;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "trail_id", updatable = false)
  private Trail trail;

  @CreationTimestamp
  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  private Date created;

  /**
   * Gets grantee.
   *
   * @return the grantee
   */
  public User getGrantee() {
    return grantee;
  }

  /**
   * Sets grantee.
   *
   * @param grantee the grantee
   */
  public void setGrantee(User grantee) {
    this.grantee = grantee;
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
   * Sets trail.
   *
   * @param trail the trail
   */
  public void setTrail(Trail trail) {
    this.trail = trail;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets created.
   *
   * @return the created
   */
  public Date getCreated() {
    return created;
  }

  
}
