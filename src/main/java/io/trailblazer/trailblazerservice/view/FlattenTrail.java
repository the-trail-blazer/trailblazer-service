package io.trailblazer.trailblazerservice.view;

import java.net.URI;

public interface FlattenTrail {
  String getName();
  String getDescription();
  Long getId();
  Boolean isTrailPublic();
  URI getHref();
}
