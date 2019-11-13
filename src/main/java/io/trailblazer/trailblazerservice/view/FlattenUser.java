package io.trailblazer.trailblazerservice.view;


import java.net.URI;

public interface FlattenUser {

  String getUsername();

  String getEmail();

  URI getHref();
}
