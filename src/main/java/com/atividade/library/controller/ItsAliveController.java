package com.atividade.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/its-alive")
@Api(tags = "Health check", value = "itsAliveController", description="Checagem de status da API")
public class ItsAliveController {
	
	@GetMapping
    @ApiOperation(value = "Checagem de status da API")
	public ResponseEntity<String> itsAlive() {
		return ResponseEntity.ok().body("It's alive!");
	}
}
