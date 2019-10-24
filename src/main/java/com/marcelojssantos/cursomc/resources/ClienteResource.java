package com.marcelojssantos.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.marcelojssantos.cursomc.domain.Cliente;
import com.marcelojssantos.cursomc.dto.ClienteDTO;
import com.marcelojssantos.cursomc.dto.ClienteNewDTO;
import com.marcelojssantos.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService serviceCliente;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		//criamos um obj. categoria que recebe a categoria do método buscar no service
		Cliente objCliente = serviceCliente.find(id);
		
		// retorna um objeto
		return ResponseEntity.ok().body(objCliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){
		Cliente obj = serviceCliente.fromDTO(objDTO);
		obj = serviceCliente.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				  buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		Cliente obj = serviceCliente.fromDTO(objDTO);
		obj.setId(id);
		obj = serviceCliente.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		serviceCliente.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listCliente = serviceCliente.findAll();
		//converte uma List<Cliente> para uma List<ClienteDTO>
		List<ClienteDTO> listClienteDTO = listCliente.stream().
				map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listClienteDTO);
	}
	
	// '/categorias/page?page=0&linesPerPage=24&orderBy=nome&direction=ASC
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findAll(
			@RequestParam(name="page", defaultValue = "0") Integer page,
			@RequestParam(name="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name="direction", defaultValue = "ASC") String direction) {
		Page<Cliente> pageCliente = serviceCliente.findPage(page, linesPerPage, orderBy, direction);
		//converte uma List<Cliente> para uma List<ClienteDTO>
		Page<ClienteDTO> pageClienteDTO = pageCliente.map(obj -> new ClienteDTO(obj)); // padrão Java 8
		return ResponseEntity.ok().body(pageClienteDTO);
	}
}
