package com.sinergi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PessoaDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private Date dataNascimento;
	private String sexo;
	private EnderecoDto endereco;
	
	public PessoaDto() {
		this.endereco = new EnderecoDto();
	}
	
	public PessoaDto(Long id, String nome, Date dataNascimento, String sexo, EnderecoDto endereco) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public EnderecoDto getEndereco() {
		return endereco;
	}
	
	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataNascimento, id, nome, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaDto other = (PessoaDto) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(sexo, other.sexo);
	}
	
}
