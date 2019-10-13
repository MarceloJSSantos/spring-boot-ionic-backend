package com.marcelojssantos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelojssantos.cursomc.domain.Pedido;
import com.marcelojssantos.cursomc.repositories.PedidoRepository;
import com.marcelojssantos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// autoinstancia o repositório por injeção de dependencia ou inversão de controle
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> objPedido = repo.findById(id);
		return objPedido.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + 
				Pedido.class.getName()));
	}
}
