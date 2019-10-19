package com.marcelojssantos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.repositories.CategoriaRepository;
import com.marcelojssantos.cursomc.services.exceptions.DataIntegrityVolationException;
import com.marcelojssantos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// autoinstancia o repositório por injeção de dependencia ou inversão de controle
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + 
				Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //garante a inserção
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); // para verificar se o id existe
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityVolationException(
					"Não é possível excluir uma Categoria que possua Produtos associados a ela!");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy,
									String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
		
		
	}
}
