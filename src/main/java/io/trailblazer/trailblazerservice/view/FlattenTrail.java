/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.view;

import java.net.URI;

/**
 * The interface Flatten trail.
 */
public interface FlattenTrail {

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Gets description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Gets id.
   *
   * @return the id
   */
  Long getId();

  /**
   * Is trail public boolean.
   *
   * @return the boolean
   */
  Boolean isTrailPublic();

  /**
   * Gets href.
   *
   * @return the href
   */
  URI getHref();
}
