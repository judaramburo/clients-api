package com.elbitgeek.clients.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elbitgeek.clients.api.dao.IClientsDao;
import com.elbitgeek.clients.api.entity.Client;

@Service
public class ClientServiceImpl implements IClientService{
	
	@Autowired
	private IClientsDao clientDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDao.findAll();
	}

}
