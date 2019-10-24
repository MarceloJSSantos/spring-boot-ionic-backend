package com.marcelojssantos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelojssantos.cursomc.domain.Cidade;
import com.marcelojssantos.cursomc.domain.Cliente;
import com.marcelojssantos.cursomc.domain.Endereco;
import com.marcelojssantos.cursomc.domain.enums.TipoCliente;
import com.marcelojssantos.cursomc.dto.ClienteDTO;
import com.marcelojssantos.cursomc.dto.ClienteNewDTO;
import com.marcelojssantos.cursomc.repositories.ClienteRepository;
import com.marcelojssantos.cursomc.repositories.EnderecoRepository;
import com.marcelojssantos.cursomc.services.exceptions.DataIntegrityVolationException;
import com.marcelojssantos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// autoinstancia o repositório por injeção de dependencia ou inversão de controle
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private EnderecoRepository repoEndereco;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repoCliente.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + 
				Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null); //garante a inserção
		obj = repoCliente.save(obj);
		repoEndereco.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repoCliente.save(newObj);
	}

	public void delete(Integer id) {
		find(id); // para verificar se o id existe
		try {
			repoCliente.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityVolationException(
					"Não é possível excluir um Cliente que possua Entidades associadas a ele!");
		}
	}
	
	public List<Cliente> findAll() {
		return repoCliente.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy,
									String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),
				orderBy);
		return repoCliente.findAll(pageRequest);
		
		
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(
				objDTO.getId(),
				objDTO.getNome(),
				objDTO.getEmail(),
				null,
				null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		//instância um cliente com os dados do ClienteNewDTO
		Cliente cli =  new Cliente(null, objDTO.getNome(), objDTO.getEmail(),objDTO.getCpfOuCnpj(),
								   TipoCliente.toEnum(objDTO.getTipoCliente()));
		//instância uma cidade com os dados do ClienteNewDTO
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		//instância um endereço com os dados do ClienteNewDTO e da cidade
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemneto(),
									objDTO.getBairro(), objDTO.getCep(), cli, cid);
		//associa o endereço ao cliente
		cli.getEnderecos().add(end);
		//associa os telefones
		cli.getTelefones().add(objDTO.getTelefone1()); // obrigatório
		//verifica se foi fornecido outro telefone (opcional)
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2()); // opcional
		}
		//verifica se foi fornecido outro telefone (opcional)
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3()); // opcional
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
