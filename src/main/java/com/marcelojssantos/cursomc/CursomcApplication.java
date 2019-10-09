package com.marcelojssantos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcelojssantos.cursomc.domain.Categoria;
import com.marcelojssantos.cursomc.domain.Cidade;
import com.marcelojssantos.cursomc.domain.Cliente;
import com.marcelojssantos.cursomc.domain.Endereco;
import com.marcelojssantos.cursomc.domain.Estado;
import com.marcelojssantos.cursomc.domain.Produto;
import com.marcelojssantos.cursomc.domain.enums.TipoCliente;
import com.marcelojssantos.cursomc.repositories.CategoriaRepository;
import com.marcelojssantos.cursomc.repositories.CidadeRepository;
import com.marcelojssantos.cursomc.repositories.ClienteRepository;
import com.marcelojssantos.cursomc.repositories.EnderecoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		
		//cria os objetos 'Cliente'
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria.silva@gmail.com", "021.777.805-68",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("(11) 2145-8598", "(11) 98565-8977"));
		
		Cliente cli2 = new Cliente(null, "Marcelo Santana", "marcelo.santana@gmail.com", "021.784.598-98", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("(21) 3315-8968", "(21) 98856-8965"));
		
		//cria os objetos 'Endereco' e associa a 'cidade'
		//como a associação é 'muitos-para-um', indicamos o 'um' no próprio construtor
		Endereco end1 = new Endereco(null, "Rua das Flores", "300", "ap. 303", "Jardins", "33.897-748", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av. Matos", "105", "sala 801", "Centro", "11.895-015", cli1, cid2);
		Endereco end3 = new Endereco(null, "Av. Mergulhão", "150", "bl. 4, ap. 201", "Campo Grande", "23.085-019", cli2, cid4);
		Endereco end4 = new Endereco(null, "Av. Francisco Bicalho", "32.516", "sl. 1208", "Centro", "26.121-210", cli2, cid5);
		
		// associa os 'Endereco' ao 'Cliente'
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3, end4));
		
		//adicionamos uma lista de 'Cliente' no BD usando seu repository
		//primeiro o 'Cliente' pois outros objetos (endereco) dependem dele 
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		//adicionamos uma lista de 'Endereco' no BD usando seu repository
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		}
}
