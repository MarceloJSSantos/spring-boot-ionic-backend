package com.marcelojssantos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcelojssantos.cursomc.domain.Cliente;
import com.marcelojssantos.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService serviceCliente;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> busca_id(@PathVariable Integer id) {
		
		//criamos um obj. categoria que recebe a categoria do método buscar no service
		Cliente objCliente = serviceCliente.buscar(id);
		
		// retorna um objeto
		return ResponseEntity.ok().body(objCliente);
	}
}
