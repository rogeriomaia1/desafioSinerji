package com.sinergi.dto;

import java.io.Serializable;
import java.util.Objects;

public class EnderecoDto  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String estado;
	private String cidade;
	private String logradouro;
	private String numero;
	private String cep;
	
	public EnderecoDto() {
	}
	
	public EnderecoDto(Long id, String estado, String cidade, String logradouro, String numero, String cep) {
		this.id = id;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, cidade, estado, id, logradouro, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoDto other = (EnderecoDto) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(estado, other.estado) && Objects.equals(id, other.id)
				&& Objects.equals(logradouro, other.logradouro) && Objects.equals(numero, other.numero);
	}
	
	
}
