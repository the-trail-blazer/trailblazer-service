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


  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Double getWeightLbs() {
    return weightLbs;
  }

  public void setWeightLbs(Double weightLbs) {
    this.weightLbs = weightLbs;
  }

  public Double getHeightInches() {
    return heightInches;
  }

  public void setHeightInches(Double heightFt) {
    this.heightInches = heightFt;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
}
