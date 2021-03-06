package com.marcelojssantos.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelojssantos.cursomc.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	//define que o campo não pode ter dado duplicado (chave candidata)
	@Column(unique = true)
	private String email;
	private String cpfOuCnpj;
	private Integer tipoCliente; //adequado para uso do Enum
	
	//'Cliente' tem vários 'Enderecos' (por isso 'List<Endereco>')
	//por fim instanciamos com a implementação de um 'ArrayList<>()'
	//cascade = CascadeType.ALL para ao exluir um cliente, exclua também os endereços associados a ele
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	/*
	 * 'Cliente' tem vários 'Telefones' (por ser uma entidade fraca, que depende de 'Cliente',
	 * optou-se por não criar uma class e sim um conjunto de Strins (Set), inclusive um 'Set'
	 * não permite repertição de um item
	 */
	//por fim instanciamos com a implementação de um 'HashSet<>()'
	@ElementCollection //mapeamento de entidade 'fraca'
	@CollectionTable(name="telefone") // define a tabela no BD
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore // trata referência cíclica (não mostra os clientes para 1 endereço)
	@OneToMany(mappedBy = "cliente")
	//'Cliente' tem vários 'Pedido' (por isso 'List<Endereco>')
	//por fim instanciamos com a implementação de um 'ArrayList<>()'
	private List<Pedido> pedidos = new ArrayList<>();
	
	//construtores
	public Cliente() {
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		//adequado para uso do Enum
		//adequado para o recebimento de um objeto 'null'
		this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getCod();
	}

	//métodos getters e setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	//adequado para uso do Enum
	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(tipoCliente);
	}

	//adequado para uso do Enum
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	//métodos hashCode e equals
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
