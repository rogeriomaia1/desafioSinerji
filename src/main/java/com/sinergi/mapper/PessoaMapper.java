package com.sinergi.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.sinergi.dto.PessoaDto;
import com.sinergi.model.Endereco;
import com.sinergi.model.Pessoa;

@ApplicationScoped
public class PessoaMapper {
	
	@Inject
	private EnderecoMapper enderecoMapper;

	public Pessoa retornaPessoaEntity(PessoaDto dto) {
        return new Pessoa(
        		dto.getId(),
                dto.getNome(),
                dto.getDataNascimento(),
                dto.getSexo()
        );
    }
	
	public PessoaDto retornaPessoaDto(Pessoa pessoa) {
        return new PessoaDto(
        		pessoa.getId(),
        		pessoa.getNome(),
        		pessoa.getDataNascimento(),
        		pessoa.getSexo(),
        		null
        );
    }
	
	public PessoaDto retornaPessoaDto(Pessoa pessoa, Endereco endereco) {
		return new PessoaDto(
				pessoa.getId(),
				pessoa.getNome(),
				pessoa.getDataNascimento(),
				pessoa.getSexo(),
				enderecoMapper.retornaEnderecoDto(endereco)
				);
	}
}
