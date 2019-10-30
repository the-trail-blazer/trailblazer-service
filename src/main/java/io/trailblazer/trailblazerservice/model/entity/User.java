package io.trailblazer.trailblazerservice.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity(name = "user_info")
@Table(uniqueConstraints = {  @UniqueConstraint(columnNames = {"user_name"}),
                              @UniqueConstraint(columnNames = {"oauth"}),
                              @UniqueConstraint(columnNames = {"email"}) },
        indexes = {@Index(columnList = "created"), @Index(columnList = "user_name")})
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

  @Column(name = "user_name", length = 50)
  private String userName;

  @Column(length = 50)
  private String oauth;

  @Column(length = 200)
  private String email;

  @OneToMany(mappedBy = "creator")
  private List<Trail> authored = new ArrayList<>();

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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getOauth() {
    return oauth;
  }

  public void setOauth(String oauth) {
    this.oauth = oauth;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
