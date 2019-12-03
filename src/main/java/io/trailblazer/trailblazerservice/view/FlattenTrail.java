/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.view;

import java.net.URI;

public interface FlattenTrail {
  String getName();
  String getDescription();
  Long getId();
  Boolean isTrailPublic();
  URI getHref();
}
