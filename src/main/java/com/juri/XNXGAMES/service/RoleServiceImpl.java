package com.juri.XNXGAMES.service;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RealmResource realmResource;

	@Override
	public void create(String roleName) {
		RoleRepresentation role = new RoleRepresentation();
		role.setName(roleName);

		realmResource
				.roles()
				.create(role);
	}

	@Override
	public List<RoleRepresentation> findAll() {
		return realmResource
					.roles()
					.list();
	}

	@Override
	public RoleRepresentation findByName(String roleName) {
		return realmResource
					.roles()
					.get(roleName)
					.toRepresentation();
	}

	@Override
	public void delete(String roleName) {
		realmResource
				.roles()
				.deleteRole(roleName);
	}

}
