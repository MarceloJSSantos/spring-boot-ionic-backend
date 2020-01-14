package com.marcelojssantos.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcelojssantos.cursomc.domain.ItemPedido;
import com.marcelojssantos.cursomc.domain.PagamentoComBoleto;
import com.marcelojssantos.cursomc.domain.Pedido;
import com.marcelojssantos.cursomc.domain.enums.EstadoPagamento;
import com.marcelojssantos.cursomc.repositories.ItemPedidoRepository;
import com.marcelojssantos.cursomc.repositories.PagamentoRepository;
import com.marcelojssantos.cursomc.repositories.PedidoRepository;
import com.marcelojssantos.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// autoinstancia o repositório por injeção de dependencia ou inversão de controle
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + 
				Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgmto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preenchePagamentoComBoleto(pgmto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepo.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepo.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
