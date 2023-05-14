package com.juri.XNXGAMES_Auth.controller;

import com.juri.XNXGAMES_Auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;
	
	@PostMapping("/roles")
	public ResponseEntity<Void> createRole(@RequestParam String roleName) {
		roleService.create(roleName);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/roles/{roleName}")
	public ResponseEntity<RoleRepresentation> findRoleByName(@PathVariable String roleName) {
		return new ResponseEntity<>(roleService.findByName(roleName), HttpStatus.OK);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<RoleRepresentation>> findRoles() {
		return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
	}
	
}
