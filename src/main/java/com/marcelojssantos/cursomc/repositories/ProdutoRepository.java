package com.marcelojssantos.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	/*
	 * @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE" +
	 *	      " obj.nome LIKE %:nome% AND cat IN :categorias")
	 * Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	 *
	 * veja https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	 * 
	 * -- faz o mesmo que os comandos anteriores
	 * -- caso use @Query... a pesquisa do framework será sobreposta por essa
	 */
	@Transactional(readOnly = true) // diz que o método não fara transações de BD
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
