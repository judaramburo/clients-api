package com.elbitgeek.clients.api.services;

import java.util.List;

import com.elbitgeek.clients.api.entity.Client;

public interface IClientService {

	public List<Client> findAll();
	
	public Client findById(Long id);
	
	public Client save(Client client);
	
	public void delete(Long id);

}
