package com.marcelojssantos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelojssantos.cursomc.domain.Cliente;
import com.marcelojssantos.cursomc.repositories.ClienteRepository;
import com.marcelojssantos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// autoinstancia o repositório por injeção de dependencia ou inversão de controle
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> objCliente = repo.findById(id);
		return objCliente.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + 
				Cliente.class.getName()));
	}
}
