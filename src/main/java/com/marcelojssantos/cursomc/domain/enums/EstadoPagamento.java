package com.marcelojssantos.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer cod;
	private String descricao;
	
	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	//método que retorna um Enum 'TipoCliente' a partir de um 'cod'
	//método static permite rodarmos ee=le sem está instanciado
	public static EstadoPagamento toEnum(Integer cod) {
		//usado para proteger, caso o valor passado seja null
		if (cod == null) {
			return null;
		}
		
		// percorre o obj 'EstadoPagamento' do ínicio ao fim
		for(EstadoPagamento x : EstadoPagamento.values()) {
			//se o 'cod' passado for igual ao 'cod' do objeto x,retorna o objeto
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		//se o 'cod' passado for inválido retorna uma exceção
		throw new IllegalArgumentException("Código Inválido! - " + cod);
	}
}
