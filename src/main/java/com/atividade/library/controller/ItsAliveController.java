package com.atividade.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/its-alive")
public class ItsAliveController {
	
	@GetMapping
	public ResponseEntity<String> itsAlive() {
		return ResponseEntity.ok().body("It's alive!");
	}
}
