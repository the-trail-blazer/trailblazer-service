/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.trailblazer.trailblazerservice.view.Username;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;


/**
 * The User characteristics.
 */
@Component
@Entity(name = "user_stats")
@Table(uniqueConstraints = {
}, indexes = {@Index(columnList = "created"),
    @Index(columnList = "user_id")}
)

public class UserCharacteristics implements Username {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_characteristics_id", updatable = false, nullable = false)
  private Long Id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  @JsonSerialize(as = Username.class)
  private User user;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false, nullable = false)
  private Date created = new Date();

  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date updated = new Date();

  private Double weightLbs;

  private Double heightInches;

  private String firstName;

  private String lastName;


  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return Id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    Id = id;
  }

  /**
   * Gets user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets user.
   *
   * @param user the user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets created.
   *
   * @return the created
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Sets created Date.
   *
   * @param created the created
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * Gets updated Date.
   *
   * @return the updated
   */
  public Date getUpdated() {
    return updated;
  }

  /**
   * Sets updated Date.
   *
   * @param updated the updated
   */
  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  /**
   * Gets weight in lbs.
   *
   * @return the weight in lbs
   */
  public Double getWeightLbs() {
    return weightLbs;
  }

  /**
   * Sets weight in lbs.
   *
   * @param weightLbs the weight in lbs
   */
  public void setWeightLbs(Double weightLbs) {
    this.weightLbs = weightLbs;
  }

  /**
   * Gets height in inches.
   *
   * @return the height in inches
   */
  public Double getHeightInches() {
    return heightInches;
  }

  /**
   * Sets height in inches.
   *
   * @param heightFt the height ft
   */
  public void setHeightInches(Double heightFt) {
    this.heightInches = heightFt;
  }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
}
