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

  public User getGrantee() {
    return grantee;
  }

  public void setGrantee(User grantee) {
    this.grantee = grantee;
  }

  public Trail getTrail() {
    return trail;
  }

  public void setTrail(Trail trail) {
    this.trail = trail;
  }

  public Long getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  
}
