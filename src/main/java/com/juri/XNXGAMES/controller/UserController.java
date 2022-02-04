package com.juri.XNXGAMES.controller;

import com.juri.XNXGAMES.dto.UserInfoDTO;
import com.juri.XNXGAMES.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestParam String userName, @RequestBody String password) {
		Response response = userService.create(userName, password);
		
		if(response.getStatus() != 201)
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserRepresentation>> findUsers() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserRepresentation> findUserById(@PathVariable String id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/users/{id}/roles")
	public ResponseEntity<Void> assignRoleToUser(@PathVariable String id, @RequestParam String roleName) {
		userService.assignRole(id, roleName);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserRepresentation> updateUser(@PathVariable String id, @RequestBody UserInfoDTO.Request request) {
		userService.update(id, request);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserRepresentation> deleteUser(@PathVariable String id) {
		userService.delete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserRepresentation> logoutUser(@PathVariable String id) {
		userService.logout(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserRepresentation> resetPassword(@PathVariable String id, @RequestBody String password) {
		userService.resetPassword(id, password);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
