package com.elbitgeek.clients.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elbitgeek.clients.api.entity.Client;
import com.elbitgeek.clients.api.services.IClientService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping("/clients")
	public List<Client> index(){
		return clientService.findAll();
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Client client = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			client = clientService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Server Error");
			response.put("error", e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(client == null) {
			response.put("message", "Client id not found");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	
	@PostMapping("/clients")
	public ResponseEntity<?> create(@RequestBody Client client) {
		
		Client newClient = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			newClient = clientService.save(client);
		} catch(DataAccessException e) {
			response.put("message", "Server Error");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Client created!");
		response.put("client", newClient);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/clients/{id}")
	public ResponseEntity<?> update(@RequestBody Client newClient, @PathVariable Long id) {
		
		Client client = clientService.findById(id);
		Client clientUpdated = null;
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(client == null) {
			response.put("message", "Client id not found");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			client.setFirstName(newClient.getFirstName());
			client.setLastName(newClient.getLastName());
			client.setEmail(newClient.getEmail());
			
			clientUpdated = clientService.save(client);
		} catch(DataAccessException e) {
			response.put("message", "Server Error");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Client updated!");
		response.put("client", clientUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			clientService.delete(id);
		} catch(DataAccessException e) {
			response.put("message", "Server Error");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "Client deleted!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
}
