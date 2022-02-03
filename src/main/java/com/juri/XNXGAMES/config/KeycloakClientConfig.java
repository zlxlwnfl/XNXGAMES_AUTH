package com.juri.XNXGAMES.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {

	@Value("${keycloak.auth-server-url}")
	private String authUrl;
	@Value("${keycloak.clientId}")
	private String clientId;
	@Value("${keycloak.credentials.secret}")
	private String clientSecret;

	public static String realm;

	@Value("${keycloak.realm}")
	public void setRealm(String value) {
		realm = value;
	}
	
	@Bean
	Keycloak keycloak() {
		return KeycloakBuilder.builder()
				.serverUrl(authUrl)
				.realm(realm)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.clientId(clientId)
				.clientSecret(clientSecret)
				.build();
	}
	
}
