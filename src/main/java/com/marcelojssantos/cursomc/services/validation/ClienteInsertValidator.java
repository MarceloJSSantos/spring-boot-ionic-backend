package com.marcelojssantos.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcelojssantos.cursomc.domain.Cliente;
import com.marcelojssantos.cursomc.domain.enums.TipoCliente;
import com.marcelojssantos.cursomc.dto.ClienteNewDTO;
import com.marcelojssantos.cursomc.repositories.ClienteRepository;
import com.marcelojssantos.cursomc.resources.exceptions.FieldMessage;
import com.marcelojssantos.cursomc.services.validation.utils.ValidadorCPF_CNPJ;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		//lista vazia utilizada para guardar os erros
		List<FieldMessage> list = new ArrayList<>();
		
		//Aqui testa e inseri erros na lista
		//Se TipoCliente = PESSOAFISICA e se o CPF não for válido
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) &&
				!ValidadorCPF_CNPJ.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
		}
		
		//Se TipoCliente = PESSOAJURIDICA e se o CNPJ não for válido
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) &&
				!ValidadorCPF_CNPJ.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
		}
		
		//verifica se há o e-mail no BD
		Cliente aux = repo.findByEmail(objDto.getEmail());
		//Se existir o E-mail
		if (aux != null) {
			list.add(new FieldMessage("Email", "E-mail já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
