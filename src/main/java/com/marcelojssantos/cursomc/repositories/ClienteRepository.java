package com.marcelojssantos.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcelojssantos.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	//implementa um métodoque busca um email no objeto Cliente
	@Transactional(readOnly = true) // diz que o método não fara transações de BD
	Cliente findByEmail(String email);
}
