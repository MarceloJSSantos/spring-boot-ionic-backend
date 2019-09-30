package com.marcelojssantos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> busca_id(@PathVariable Integer id) {
		
		//criamos um obj. categoria que recebe a categoria do método buscar no service
		Categoria objCategoria = serviceCategoria.buscar(id);
		
		// retorna um objeto
		return ResponseEntity.ok().body(objCategoria);
	}
}
