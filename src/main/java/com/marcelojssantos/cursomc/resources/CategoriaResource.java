package com.marcelojssantos.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcelojssantos.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		//teste instânciando categoria
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		/*
		 * Criamos java.util.List
		 * instanciamos a lista de um java.util.ArrayList, pois um List, como é
		 * uma interface, não pode ser instanciado
		 */
		List<Categoria> lista = new ArrayList<>();
		// adicionamos os objetos
		lista.add(cat1);
		lista.add(cat2);

		// retorna a lista
		return lista;
	}
}
