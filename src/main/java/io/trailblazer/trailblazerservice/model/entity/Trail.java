package io.trailblazer.trailblazerservice.model.entity;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.LineString;
import java.util.Date;
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
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(
    indexes = @Index(columnList ="date_created")
)
public class Trail {

  @NonNull
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "trail_id", nullable = false, unique = true)
  private Long id;

  private boolean trailPublic;

  @CreationTimestamp
  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_created", updatable = false)
  private Date created;


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "creator_id", updatable = false)
  private User creator;


  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(contentUsing = GeometryDeserializer.class)
  @Column(name = "trail_path", columnDefinition = "linestring")
  private LineString path;


  @Column(nullable = false)
  private String name;

  private String description;


  private String imageUrl;

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

  public LineString getPath() {
    return path;
  }

  public void setPath(LineString path) {
    this.path = path;
  }
}
