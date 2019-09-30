package com.marcelojssantos.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	// criação de atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	// Iniciamos uma coleção de Categorias para associação * Produto p/ * Categorias
	@ManyToMany // relacionamento muito(*) p/ muitos(*)
	// Sendo o mapeamento 'ManyToMany', definimos a tabela que será criada
	@JoinTable
	(
		name = "produto_categoria", // nome da tabela a ser criada
		joinColumns = @JoinColumn(name="produto_id"), // nome da coluna  chave primária
		inverseJoinColumns = @JoinColumn(name="categoria_id") // nome da coluna chave estrageira
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	// Construtores
	public Produto() {
		
	}

	// Todos atrinbutos, menos categorias, pois já foi instanciado na criação
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	//Getters e Setters (métodos de acesso aos atributos)
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
