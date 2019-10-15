package com.marcelojssantos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcelojssantos.cursomc.domain.Pedido;
import com.marcelojssantos.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService servicePedido;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		
		//criamos um obj. 'Pedido' que recebe um pedido do método buscar no service
		Pedido obj = servicePedido.find(id);
		
		// retorna um objeto
		return ResponseEntity.ok().body(obj);
	}
}
