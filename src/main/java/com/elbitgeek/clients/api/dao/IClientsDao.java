package com.elbitgeek.clients.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.elbitgeek.clients.api.entity.Client;

public interface IClientsDao extends CrudRepository<Client, Long>{

}
