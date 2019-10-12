package com.marcelojssantos.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.marcelojssantos.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento  {
	//nas subclass só tem essa linha, não o 'implements Serializable{'
	private static final long serialVersionUID = 1L;
	
	//atributos
	private Date dataVencimento;
	private Date dataPagamento;
	
	//contrutores
	public PagamentoComBoleto() {
	}

	// como esta é uma subclass cria um construtor baseado na superclass
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	//métodos getters e setters
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
