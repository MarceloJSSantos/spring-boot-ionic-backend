package com.marcelojssantos.cursomc.domain;

import java.io.Serializable;

/*
 * implements Serializable: faz com que a class possa ser convertida em bytes
 * podendo ser encaminhada por rede ou para arquivos
 * devemos adicionar um "serialVersionUID"
 */
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;

	//atributos
	private Integer id;
	private String nome;
	
	//contrutores
	public Categoria() {
		
	}

	//Getters e Setters (métodos de acesso aos atributos)
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//hashCode e equals (métodos para manter a integridade do objeto)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
}
