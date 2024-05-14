package com.sinergi.enums;

public enum ErroEnum {

	ER0001("Cliente já cadastrado", "ER-0001"),
	ER0002("Erro ao tentar salvar cliente.", "ER-0002"),
	ER0003("Erro ao tentar atualizar cliente.", "ER-0003"),
	ER0004("Erro ao tentar remover cliente.", "ER-0004");

	private String mensagem;
	private String codigo;

	ErroEnum(String mensagem, String codigo) {
		this.mensagem = mensagem;
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
