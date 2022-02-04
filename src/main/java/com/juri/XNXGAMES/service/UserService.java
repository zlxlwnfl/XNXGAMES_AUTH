package com.juri.XNXGAMES.service;

import com.juri.XNXGAMES.dto.UserInfoDTO;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.List;

public interface UserService {

	Response create(String userName, String password);
	List<UserRepresentation> findAll();
	UserRepresentation findById(String userId);
	void assignRole(String userId, String roleName);
	void update(String userId, UserInfoDTO.Request request);
	void delete(String userId);
	void logout(String userId);
	void resetPassword(String userId, String password);

}
