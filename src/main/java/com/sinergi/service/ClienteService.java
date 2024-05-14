package com.sinergi.service;

import java.util.List;

import com.sinergi.dto.PessoaDto;


public interface ClienteService {
	
	PessoaDto salvarCliente(PessoaDto pessoa) throws Exception;
	
	PessoaDto atualizarCliente(PessoaDto pessoa) throws Exception;
	
	Boolean removerCliente(PessoaDto pessoa) throws Exception;
	
	List<PessoaDto> consultarCliente(PessoaDto pessoa);

}
