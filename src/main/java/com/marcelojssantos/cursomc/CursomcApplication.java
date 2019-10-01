package com.marcelojssantos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.domain.Produto;
import com.marcelojssantos.cursomc.repositories.CategoriaRepository;
import com.marcelojssantos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	// método herdado de CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		// criamos os objetos 'Categoria'
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Saúde");

		// criamos os objetos 'Produto'
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		Produto prod4 = new Produto(null, "Estetoscópio", 750.00);
		
		// Associamos as 'Categoria' a seus 'Produto'
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		cat3.getProdutos().addAll(Arrays.asList(prod4));
		
		// Associamos os 'Produto' as suas 'Categoria'
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		prod4.getCategorias().addAll(Arrays.asList(cat3));
		
		//adicionamos uma lista de 'Categoria' no BD usando seu repository
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		//adicionamos uma lista de 'Produto' no BD usando seu repository
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4));
		
	}

}
