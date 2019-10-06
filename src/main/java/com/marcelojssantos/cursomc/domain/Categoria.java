package com.marcelojssantos.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * implements Serializable: faz com que a class possa ser convertida em bytes
 * podendo ser encaminhada por rede ou para arquivos
 * devemos adicionar um "serialVersionUID"
 */
@Entity
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//atributos
	private Integer id;
	private String nome;
	
	@JsonManagedReference // Para quando for retornar o Json processa os produtos
	// Iniciamos uma coleção de Produtos para associação 1 Categoria p/ * Produtos
	// relacionamento muito(*) p/ muitos(*), aproveitando o mapeamento já feito do "outro lado"
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();
	
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
	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/* 
	 * hashCode e equals (métodos para manter a integridade do objeto
	 * (compara pelo conteúdo não pelo ponteiro))
	 */
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
