/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.view;


import java.net.URI;

/**
 * The interface Flatten user.
 */
public interface FlattenUser {

  /**
   * Gets username.
   *
   * @return the username
   */
  String getUsername();

  /**
   * Gets email.
   *
   * @return the email
   */
  String getEmail();

  /**
   * Gets href.
   *
   * @return the href
   */
  URI getHref();
}
