package io.trailblazer.trailblazerservice.model.entity;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import io.trailblazer.trailblazerservice.view.FlattenTrail;
import io.trailblazer.trailblazerservice.view.TrailGeometry;
import io.trailblazer.trailblazerservice.view.UserName;
import java.net.URI;
import java.util.Date;
import javax.annotation.PostConstruct;
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


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "creator_id", updatable = false)
  @JsonSerialize(as = UserName.class)
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

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }

  public Boolean isTrailPublic() {
    return trailPublic;
  }

  public void setTrailPublic(boolean trailPublic) {
    this.trailPublic = trailPublic;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public String getImageUrl() {
    return imageUrl;
  }

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

  @Autowired
  public void setLinks(EntityLinks links) {
    Trail.links = links;
  }


}
