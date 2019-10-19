package com.marcelojssantos.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.dto.CategoriaDTO;
import com.marcelojssantos.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		//criamos um obj. 'Categoria' que recebe uma categoria do método buscar no service
		Categoria objCategoria = serviceCategoria.find(id);
		
		// retorna um objeto
		return ResponseEntity.ok().body(objCategoria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = serviceCategoria.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				  buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = serviceCategoria.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		serviceCategoria.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listCategoria = serviceCategoria.findAll();
		//converte uma List<Categoria> para uma List<CategoriaDTO>
		List<CategoriaDTO> listCategoriaDTO = listCategoria.stream().
				map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listCategoriaDTO);
	}
	
	// '/categorias/page?page=0?linesPerPage=24?orderBy="nome"?direction="ASC"
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findAll(
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name="direction", defaultValue = "ASC") String direction) {
		Page<Categoria> pageCategoria = serviceCategoria.findPage(page, linesPerPage, orderBy, direction);
		//converte uma List<Categoria> para uma List<CategoriaDTO>
		Page<CategoriaDTO> pageCategoriaDTO = pageCategoria.map(obj -> new CategoriaDTO(obj)); // padrão Java 8
		return ResponseEntity.ok().body(pageCategoriaDTO);
	}
}
