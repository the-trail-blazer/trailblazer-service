package io.trailblazer.trailblazerservice.model.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity(name = "user_blazer")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", updatable = false, nullable = false)
  private Long id;


  @CreationTimestamp
  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  private Date created;

  @OneToMany(mappedBy = "trail")
  private List<Trail> authored;

  public List<Trail> getAuthored() {
    return authored;
  }

  public void setAuthored(List<Trail> authored) {
    this.authored = authored;
  }

  public Long getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }
}
