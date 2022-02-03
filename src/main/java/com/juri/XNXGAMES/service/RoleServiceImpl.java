package com.juri.XNXGAMES.service;

import com.juri.XNXGAMES.config.KeycloakClientConfig;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final Keycloak keycloak;

	@Override
	public void create(String roleName) {
		RoleRepresentation role = new RoleRepresentation();
		role.setName(roleName);
		
		keycloak
				.realm(KeycloakClientConfig.realm)
				.roles()
				.create(role);
	}

	@Override
	public List<RoleRepresentation> findAll() {
		return keycloak
					.realm(KeycloakClientConfig.realm)
					.roles()
					.list();
	}

	@Override
	public RoleRepresentation findByName(String roleName) {
		return keycloak
					.realm(KeycloakClientConfig.realm)
					.roles()
					.get(roleName)
					.toRepresentation();
	}

	@Override
	public void delete(String roleName) {
		keycloak
				.realm(KeycloakClientConfig.realm)
				.roles()
				.deleteRole(roleName);
	}

}
