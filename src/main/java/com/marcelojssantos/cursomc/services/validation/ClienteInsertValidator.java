package com.marcelojssantos.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.marcelojssantos.cursomc.domain.enums.TipoCliente;
import com.marcelojssantos.cursomc.dto.ClienteNewDTO;
import com.marcelojssantos.cursomc.resources.exceptions.FieldMessage;
import com.marcelojssantos.cursomc.services.validation.utils.ValidadorCPF_CNPJ;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
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
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
