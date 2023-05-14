package com.juri.XNXGAMES_Auth.service;

import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface RoleService {

	void create(String roleName);
	List<RoleRepresentation> findAll();
	RoleRepresentation findByName(String roleName);
	void delete(String roleName);
	
}
