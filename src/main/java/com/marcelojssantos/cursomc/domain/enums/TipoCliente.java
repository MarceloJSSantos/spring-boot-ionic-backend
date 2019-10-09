package com.marcelojssantos.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(Integer cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod) {
		//usado para proteger, caso o valor passado seja null
		if (cod == null) {
			return null;
		}
		
		// percorre o obj 'TipoCliente' do ínicio ao fim
		for(TipoCliente x : TipoCliente.values()) {
			//se o 'cod' passado for igual ao 'cod' do objeto x,retorna o objeto
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		//se o 'cod' passado for inválido retorna uma exceção
		throw new IllegalArgumentException("Código Inválido! - " + cod);
	}
}
