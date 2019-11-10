package com.marcelojssantos.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcelojssantos.cursomc.domain.Produto;
import com.marcelojssantos.cursomc.dto.ProdutoDTO;
import com.marcelojssantos.cursomc.resources.utils.URL;
import com.marcelojssantos.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService serviceProduto;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		
		//criamos um obj. 'Produto' que recebe um pedido do método buscar no service
		Produto obj = serviceProduto.find(id);
		
		// retorna um objeto
		return ResponseEntity.ok().body(obj);
	}
	
	// '/categorias/page?page=0&linesPerPage=24&orderBy=nome&direction=ASC
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findAll(
			@RequestParam(name="nome", defaultValue = "") String nome,
			@RequestParam(name="categorias", defaultValue = "") String categorias,
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name="direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> pageProduto = serviceProduto.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		//converte uma List<Produto> para uma List<ProdutoDTO>
		Page<ProdutoDTO> pageProdutoDTO = pageProduto.map(obj -> new ProdutoDTO(obj)); // padrão Java 8
		return ResponseEntity.ok().body(pageProdutoDTO);
	}
}