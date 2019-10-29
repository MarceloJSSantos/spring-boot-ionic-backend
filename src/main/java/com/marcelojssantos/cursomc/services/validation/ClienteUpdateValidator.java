package com.marcelojssantos.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.marcelojssantos.cursomc.domain.Cliente;
import com.marcelojssantos.cursomc.dto.ClienteDTO;
import com.marcelojssantos.cursomc.repositories.ClienteRepository;
import com.marcelojssantos.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request; //permite obter o id da requisição
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		//pegar o id passado na requisição quando update
		@SuppressWarnings("unchecked") //para não aparecer mensagem
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		Integer urlId = Integer.parseInt(map.get("id")); // guarda o id em uma variável integer
		
		//lista vazia utilizada para guardar os erros
		List<FieldMessage> list = new ArrayList<>();
		
		//Aqui testa e inseri erros na lista
		//verifica se há o e-mail no BD
		Cliente aux = repo.findByEmail(objDto.getEmail());
		//Se existir o E-mail e não for o mesmo cliente (pelo Id)
		if (aux != null && !aux.getId().equals(urlId)) {
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
