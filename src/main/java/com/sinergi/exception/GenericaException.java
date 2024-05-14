package com.sinergi.exception;

public class GenericaException extends Exception {

	private static final long serialVersionUID = 1L;
	private String codigo;

	public GenericaException(String mensagem, String codigo) {
	        super(mensagem);
	        this.codigo = codigo;
	    }
}
