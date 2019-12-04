/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.model.entity;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import io.trailblazer.trailblazerservice.view.FlattenTrail;
import io.trailblazer.trailblazerservice.view.TrailGeometry;
import io.trailblazer.trailblazerservice.view.Username;
import java.net.URI;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * The types of trails.
 */
@Entity
@Table(
    indexes = @Index(columnList = "date_created"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"creator_id", "trail_name"})}
)
@Component
//@JsonIgnoreProperties(value = {"created", "updated", "href"},
//    allowGetters = true, ignoreUnknown = true)
public class Trail implements FlattenTrail, TrailGeometry {

  private static EntityLinks links;


  @NonNull
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "trail_id", nullable = false, unique = true)
  private Long id;

  @Column(name = "trail_public", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
  private boolean trailPublic;

  @CreationTimestamp
  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_created", updatable = false)
  private Date created;


  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "creator_id", updatable = false)
  @JsonSerialize(as = Username.class)
  private User creator;

  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(contentUsing = GeometryDeserializer.class)
  @Column(columnDefinition = "geometry")
  private Geometry geometry;

  @Column(name = "trail_name", nullable = false)
  private String name;

  private String description;

  private String imageUrl;


  public Geometry getGeometry() {
    return geometry;
  }

  /**
   * Sets Trail geometry.
   *
   * @param geometry the geometry
   */
  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  public String getName() {
    return name;
  }

  /**
   * Sets Trail name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  /**
   * Gets date created.
   *
   * @return the created
   */
  public Date getCreated() {
    return created;
  }

  public Boolean isTrailPublic() {
    return trailPublic;
  }

  /**
   * Sets trail public.
   *
   * @param trailPublic the trail public
   */
  public void setTrailPublic(boolean trailPublic) {
    this.trailPublic = trailPublic;
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
   * Sets creator.
   *
   * @param creator the creator
   */
  public void setCreator(User creator) {
    this.creator = creator;
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
   * Sets image url.
   *
   * @param imageUrl the image url
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public URI getHref() {
    return links.linkForSingleResource(Trail.class, id).toUri();
  }

  @PostConstruct
  private void initLinks() {
    String ignore = links.toString();
  }

  /**
   * Sets links.
   *
   * @param links the links
   */
  @Autowired
  public void setLinks(EntityLinks links) {
    Trail.links = links;
  }


}
