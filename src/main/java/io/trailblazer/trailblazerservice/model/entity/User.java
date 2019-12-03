/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.trailblazer.trailblazerservice.view.FlattenUser;
import io.trailblazer.trailblazerservice.view.Username;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "authenticated_user")
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_name"}),
    @UniqueConstraint(columnNames = {"oauth_key"}),
    @UniqueConstraint(columnNames = {"email"})},
    indexes = {@Index(columnList = "created"),
        @Index(columnList = "user_name")})
@JsonSerialize(as = FlattenUser.class)
public class User implements FlattenUser, Username {

  private static EntityLinks links;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_id", columnDefinition = "BINARY(16)",
      nullable = false, updatable = false)
  private UUID id;

  @CreationTimestamp
  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  private Date created;

  @Column(name = "user_name", length = 50)
  private String username;

  @NonNull
  @Column(name = "oauth_key", nullable = false, updatable = false, unique = true)
  private String oauthKey;

  @Column(length = 200)
  private String email;

  @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER)
  private List<Trail> authored = new ArrayList<>();

  public UUID getId() {
    return id;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String userName) {
    this.username = userName;
  }

  @NonNull
  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(@NonNull String oauthKey) {
    this.oauthKey = oauthKey;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Trail> getAuthored() {
    return authored;
  }

  public URI getHref() {
    return links.linkForSingleResource(User.class, id).toUri();
  }

  @PostConstruct
  private void initLinks() {
    String ignore = links.toString();
  }

  @Autowired
  public void setLinks(EntityLinks links) {
    User.links = links;
  }



}
