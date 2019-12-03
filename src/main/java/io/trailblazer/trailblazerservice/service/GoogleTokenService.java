/*
 * This work is Copyright, 2019, Isaac Lindland, Joel Bond, Khizar Saleem, Justin Dominguez
 * All rights reserved
 */

package io.trailblazer.trailblazerservice.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import io.trailblazer.trailblazerservice.model.entity.User;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class GoogleTokenService implements ResourceServerTokenServices {

  private final String clientId;
  private final UserService userService;
  private final AccessTokenConverter converter = new DefaultAccessTokenConverter();

  @Autowired
  public GoogleTokenService(@Value("${oauth.clientId}") String clientId,
      UserService userService) {
    this.clientId = clientId;
    this.userService = userService;
  }

  @Override
  public OAuth2Authentication loadAuthentication(String token)
      throws AuthenticationException, InvalidTokenException {
    try {
      HttpTransport transport = new NetHttpTransport();
      JacksonFactory jsonFactory = new JacksonFactory();
      GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
          .setAudience(Collections.singletonList(clientId))
          .build();
      GoogleIdToken idToken = verifier.verify(token);
      if (idToken != null) {
        Payload payload = idToken.getPayload();

        User user = userService.getOrCreateUser(payload);
        Collection<GrantedAuthority> grants =
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication base =
            new UsernamePasswordAuthenticationToken(user, token, grants);
        OAuth2Request request = converter.extractAuthentication(payload).getOAuth2Request();
        return new OAuth2Authentication(request, base);
      } else {
        throw new BadCredentialsException(token);
      }
    } catch (GeneralSecurityException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public OAuth2AccessToken readAccessToken(String s) {
    return null;
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public void badCredentials() {
  }
}
