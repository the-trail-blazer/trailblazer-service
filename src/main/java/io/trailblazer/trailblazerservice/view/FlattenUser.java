/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.view;


import java.net.URI;

public interface FlattenUser {

  String getUsername();

  String getEmail();

  URI getHref();
}
