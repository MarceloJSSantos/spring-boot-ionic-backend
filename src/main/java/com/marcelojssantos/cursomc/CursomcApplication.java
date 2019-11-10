package com.marcelojssantos.cursomc;

import java.text.SimpleDateFormat;
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
import com.marcelojssantos.cursomc.domain.ItemPedido;
import com.marcelojssantos.cursomc.domain.Pagamento;
import com.marcelojssantos.cursomc.domain.PagamentoComBoleto;
import com.marcelojssantos.cursomc.domain.PagamentoComCartao;
import com.marcelojssantos.cursomc.domain.Pedido;
import com.marcelojssantos.cursomc.domain.Produto;
import com.marcelojssantos.cursomc.domain.enums.EstadoPagamento;
import com.marcelojssantos.cursomc.domain.enums.TipoCliente;
import com.marcelojssantos.cursomc.repositories.CategoriaRepository;
import com.marcelojssantos.cursomc.repositories.CidadeRepository;
import com.marcelojssantos.cursomc.repositories.ClienteRepository;
import com.marcelojssantos.cursomc.repositories.EnderecoRepository;
import com.marcelojssantos.cursomc.repositories.EstadoRepository;
import com.marcelojssantos.cursomc.repositories.ItemPedidoRepository;
import com.marcelojssantos.cursomc.repositories.PagamentoRepository;
import com.marcelojssantos.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	// método herdado de CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		// cria os objetos 'Categoria'
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Saúde");
		
		// cria os objetos 'Produto'
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		Produto prod4 = new Produto(null, "Mesa de Escritáorio", 300.00);
		Produto prod5 = new Produto(null, "Toalha", 50.00);
		Produto prod6 = new Produto(null, "Colcha", 200.00);
		Produto prod7 = new Produto(null, "TV true color", 1200.00);
		Produto prod8 = new Produto(null, "Roçadeira", 800.00);
		Produto prod9 = new Produto(null, "Abajour", 100.00);
		Produto prod10 = new Produto(null, "Pendente", 180.00);
		Produto prod11 = new Produto(null, "Shampoo", 90.00);
		Produto prod12 = new Produto(null, "Estetostópico", 820.39);
		
		// associa as 'Categoria' a seus 'Produto'
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProdutos().addAll(Arrays.asList(prod8));
		cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat7.getProdutos().addAll(Arrays.asList(prod11));
		cat8.getProdutos().addAll(Arrays.asList(prod12));
		
		// associa os 'Produto' as suas 'Categoria'
		prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		prod3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		prod4.getCategorias().addAll(Arrays.asList(cat2));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod7.getCategorias().addAll(Arrays.asList(cat4));
		prod8.getCategorias().addAll(Arrays.asList(cat5));
		prod9.getCategorias().addAll(Arrays.asList(cat6));
		prod10.getCategorias().addAll(Arrays.asList(cat6));
		prod11.getCategorias().addAll(Arrays.asList(cat7));
		prod12.getCategorias().addAll(Arrays.asList(cat8));
		
		//após criar o repository
		//adiciona uma lista de 'Categoria' no BD usando seu repository
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6,
												  cat7, cat8));
		
		//após criar o repository
		//adiciona uma lista de 'Produto' no BD usando seu repository
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6,
												prod7, prod8, prod9, prod10, prod11, prod12));
		
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
		
		//após criar o repository
		//adiciona uma lista de 'Estado' no BD usando seu repository
		//primeiro o 'Estado' pois outros objetos dependem dele
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		
		//após criar o repository
		//adiciona uma lista de 'Cidade' no BD usando seu repository
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
		
		//após criar os repositories
		//adiciona uma lista de 'Cliente' no BD usando seu repository
		//primeiro o 'Cliente' pois outros objetos (endereco) dependem dele 
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		
		//após criar o repository
		//adiciona uma lista de 'Endereco' no BD usando seu repository
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		
		//máscara de formatação para um 'instante' dia e horário
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		//cria os objetos 'Pedido'
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32:05"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35:45"), cli1, end2);
		Pedido ped3 = new Pedido(null, sdf.parse("12/10/2019 03:12:25"), cli2, end3);
		
		//cria os objetos 'Pagamento' e define um 'Pagamento' para seu 'Pedido'
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00:00"), null);
		ped2.setPagamento(pagto2);
		Pagamento pagto3 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped3, sdf.parse("14/10/2019 00:00:00"), sdf.parse("12/10/2019 00:00:00"));
		ped3.setPagamento(pagto3);
		
		// associa os 'Pedido' ao 'Cliente'
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		cli2.getPedidos().addAll(Arrays.asList(ped3));
		
		//após criar o repository
		//adiciona uma lista de 'Pedido' no BD usando seu repository
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));
		
		//após criar o repository (basta criar da SuperClass)
		//adiciona uma lista de 'Pagamento' no BD usando seu repository
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2, pagto3));
		
		//cria os objetos 'ItemPedido'
		ItemPedido itemped1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido itemped2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido itemped3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
		ItemPedido itemped4 = new ItemPedido(ped3, prod3, 55.00, 5, 400.00);
		ItemPedido itemped5 = new ItemPedido(ped3, prod4, 185.56, 1, 750.00);
		
		// associa os 'ItemPedido' ao 'Pedido'
		ped1.getItens().addAll(Arrays.asList(itemped1, itemped2));
		ped2.getItens().addAll(Arrays.asList(itemped3));
		ped3.getItens().addAll(Arrays.asList(itemped4, itemped5));
		
		// associa os 'ItemPedido' ao 'Produto'
		prod1.getItens().addAll(Arrays.asList(itemped1));
		prod2.getItens().addAll(Arrays.asList(itemped3));
		prod3.getItens().addAll(Arrays.asList(itemped2, itemped4));
		prod4.getItens().addAll(Arrays.asList(itemped5));
		
		//após criar o repository
		//adiciona uma lista de 'Pedido' no BD usando seu repository
		itemPedidoRepository.saveAll(Arrays.asList(itemped1, itemped2, itemped3, itemped4, itemped5));
	}
}
