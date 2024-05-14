package com.sinergi.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.sinergi.dto.EnderecoDto;
import com.sinergi.dto.PessoaDto;
import com.sinergi.model.Endereco;

@ApplicationScoped
public class EnderecoMapper {
	
	@Inject
	private PessoaMapper pessoaMapper;
	
	public Endereco retornaEnderecoEntity(EnderecoDto dto, PessoaDto pDto) {
	        return new Endereco(
	               dto.getEstado(), 
	               dto.getCidade(), 
	               dto.getLogradouro(),
	               dto.getNumero(), 
	               dto.getCep(),
	               pessoaMapper.retornaPessoaEntity(pDto)
	        );
	    }

	public EnderecoDto retornaEnderecoDto(Endereco entidade) {
	
        return new EnderecoDto(
        		entidade.getId(),
        		entidade.getEstado(), 
        		entidade.getCidade(), 
        		entidade.getLogradouro(),
        		entidade.getNumero(), 
        		entidade.getCep()
        );
    }
}
