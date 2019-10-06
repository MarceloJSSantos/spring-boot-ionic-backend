package com.marcelojssantos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.repositories.CategoriaRepository;
import com.marcelojssantos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// autoinstancia o repositório por injeção de dependencia ou inversão de controle
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> objCategoria = repo.findById(id);
		return objCategoria.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + 
				Categoria.class.getName()));
	}
}
