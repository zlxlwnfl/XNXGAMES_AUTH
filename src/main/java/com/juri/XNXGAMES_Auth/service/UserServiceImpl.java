package com.juri.XNXGAMES_Auth.service;

import com.juri.XNXGAMES_Auth.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final RealmResource realmResource;
	private final RoleService roleService;
	
	@Override
	public Response create(String userName, String password) {
		CredentialRepresentation cr = prepareCR(password);
		
		UserRepresentation ur = new UserRepresentation();
		ur.setUsername(userName);
		ur.setCredentials(Arrays.asList(cr));
		ur.setEnabled(true);
		
		return realmResource
				.users()
				.create(ur);
	}

	private CredentialRepresentation prepareCR(String password) {
		CredentialRepresentation cr = new CredentialRepresentation();
		cr.setTemporary(false);
		cr.setType(CredentialRepresentation.PASSWORD);
		cr.setValue(password);

		return cr;
	}

	@Override
	public List<UserRepresentation> findAll() {
		return realmResource
				.users()
				.list();
	}

	@Override
	public UserRepresentation findById(String userId) {
		return realmResource
				.users()
				.get(userId)
				.toRepresentation();
	}

	@Override
	public void assignRole(String userId, String roleName) {
		realmResource
				.users()
				.get(userId)
				.roles()
				.realmLevel()
				.add(Arrays.asList(roleService.findByName(roleName)));
	}

	@Override
	public void update(String userId, UserInfoDTO.Request request) {
		UserRepresentation ur = realmResource
				.users()
				.get(userId)
				.toRepresentation();

		ur.setFirstName(request.getFirstName());
		ur.setLastName(request.getLastName());
		ur.setEmail(request.getEmail());

		realmResource
				.users()
				.get(userId)
				.update(ur);
	}

	@Override
	public void delete(String userId) {
		realmResource
				.users()
				.get(userId)
				.remove();
	}

	@Override
	public void logout(String userId) {
		realmResource
				.users()
				.get(userId)
				.logout();
	}

	@Override
	public void resetPassword(String userId, String password) {
		CredentialRepresentation cr = prepareCR(password);

		realmResource
				.users()
				.get(userId)
				.resetPassword(cr);
	}

}
