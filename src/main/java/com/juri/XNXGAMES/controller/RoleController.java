package com.juri.XNXGAMES.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juri.XNXGAMES.service.RoleService;

import lombok.AllArgsConstructor;

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
