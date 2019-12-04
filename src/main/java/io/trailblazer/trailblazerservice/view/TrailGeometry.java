/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.view;

import com.vividsolutions.jts.geom.Geometry;

/**
 * The interface Trail geometry.
 */
public interface TrailGeometry {

  /**
   * Gets geometry.
   *
   * @return the geometry
   */
  Geometry getGeometry();

}
