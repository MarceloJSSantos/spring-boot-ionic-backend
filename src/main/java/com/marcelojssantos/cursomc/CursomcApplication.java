package com.marcelojssantos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.domain.Cidade;
import com.marcelojssantos.cursomc.domain.Estado;
import com.marcelojssantos.cursomc.domain.Produto;
import com.marcelojssantos.cursomc.repositories.CategoriaRepository;
import com.marcelojssantos.cursomc.repositories.CidadeRepository;
import com.marcelojssantos.cursomc.repositories.EstadoRepository;
import com.marcelojssantos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	// método herdado de CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		// cria os objetos 'Categoria'
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Saúde");
		
		// cria os objetos 'Produto'
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		Produto prod4 = new Produto(null, "Estetoscópio", 750.00);
		
		// associa as 'Categoria' a seus 'Produto'
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		cat3.getProdutos().addAll(Arrays.asList(prod4));
		
		// associa os 'Produto' as suas 'Categoria'
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		prod4.getCategorias().addAll(Arrays.asList(cat3));
		
		//adicionamos uma lista de 'Categoria' no BD usando seu repository
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		//adicionamos uma lista de 'Produto' no BD usando seu repository
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4));
		
		//cria os objetos 'Estado'
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Rio de Janeiro");
		
		//cria os objetos 'Cidade' e associa o 'estado'
		//como a associação é 'muitos-para-um', indicamos o 'um' no próprio construtor
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		Cidade cid4 = new Cidade(null, "Rio de Janeiro", est3);
		Cidade cid5 = new Cidade(null, "Niterói", est3);
		
		// associa as 'cidade' ao 'estado'
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		est3.getCidades().addAll(Arrays.asList(cid4, cid5));
		
		//adicionamos uma lista de 'Estado' no BD usando seu repository
		//primeiro o 'Estado' pois outros objetos dependem dele
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		//adicionamos uma lista de 'Cidade' no BD usando seu repository
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4, cid5));
		}
}
