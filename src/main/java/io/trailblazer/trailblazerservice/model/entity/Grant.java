package io.trailblazer.trailblazerservice.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "visibility_grant")
public class Grant {

  @NonNull
  @Id
  @Column(name = "grant_id", nullable = false, unique = true)
  private Long id;

  @CreationTimestamp
  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  private Date created;

  public Long getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  
}
