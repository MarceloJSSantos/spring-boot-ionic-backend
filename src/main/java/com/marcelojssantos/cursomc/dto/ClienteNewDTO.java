package com.marcelojssantos.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.marcelojssantos.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//atributos do 'Cliente'
	@NotEmpty(message = "Preenchimento obrigatório.")
	@Length(min = 5, max = 200, message = "O valor deve ser entre 5 e 200 caracteres.")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório.")
	@Email(message = "Não é um e-mail válido.")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório.")
	private String cpfOuCnpj;
	
	private Integer tipoCliente; //adequado para uso do Enum
	
	//atributos do 'Endereço'
	@NotEmpty(message = "Preenchimento obrigatório.")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimento obrigatório.")
	private String numero;
	
	private String complemneto;
	
	@NotEmpty(message = "Preenchimento obrigatório.")
	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório.")
	private String cep;
	
	//telefones (1 obrigatório + 2 opcionais)
	@NotEmpty(message = "Preenchimento obrigatório.")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	//Id de 'Cidade' do 'Endereço'
	private Integer cidadeId;
	
	public ClienteNewDTO() {
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

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemneto() {
		return complemneto;
	}

	public void setComplemneto(String complemneto) {
		this.complemneto = complemneto;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
}
