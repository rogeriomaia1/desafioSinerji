package com.sinergi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.sinergi.dao.ClienteDao;
import com.sinergi.dao.EnderecoDao;
import com.sinergi.dto.PessoaDto;
import com.sinergi.enums.ErroEnum;
import com.sinergi.exception.GenericaException;
import com.sinergi.mapper.EnderecoMapper;
import com.sinergi.mapper.PessoaMapper;
import com.sinergi.model.Endereco;
import com.sinergi.model.Pessoa;
import com.sinergi.service.ClienteService;

@Named
@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    private ClienteDao clienteDao;
    
    @Inject
    private EnderecoDao enderecoDao;
    
	@Inject
    private PessoaMapper pessoaMapper;
	
	@Inject
	private EnderecoMapper enderecoMapper;

	@Override
	@Transactional
	public PessoaDto salvarCliente(PessoaDto pessoaDto) throws Exception {
	    Pessoa pessoa = pessoaMapper.retornaPessoaEntity(pessoaDto);
	    Endereco endereco = enderecoMapper.retornaEnderecoEntity(pessoaDto.getEndereco(), pessoaDto);
	    
	    this.validarRegras(pessoaDto);
	    
	    if (!pessoaDto.getNome().isEmpty()) {
	        
	    	clienteDao.salvar(pessoa);
		    
		    endereco.setPessoa(pessoa);
		    enderecoDao.salvar(endereco);
	        
	        return pessoaMapper.retornaPessoaDto(pessoa);
	    }
	    
	    throw new Exception(ErroEnum.ER0002.getMensagem());
	}


    /**
     * Método responsável por manter as regras do cadastro de cliente
     */
    private void validarRegras(PessoaDto pessoaDto) throws GenericaException {
    	Pessoa pessoaConsultada = clienteDao.buscarPessoaPorNome(pessoaDto.getNome());
		
    	if (pessoaConsultada != null) {
    		 throw new GenericaException(ErroEnum.ER0001.getMensagem(), ErroEnum.ER0001.getCodigo());
    	}
	}

    /**
     * Método responsável por manter as regras de alteração do cliente
     */
    private void validarRegrasAlterar(PessoaDto pessoaDto) throws GenericaException {
    	List<Pessoa> listaPessoa = clienteDao.buscarPessoas(pessoaDto);
    	
    	if (!listaPessoa.isEmpty()) {
    		throw new GenericaException(ErroEnum.ER0001.getMensagem(), ErroEnum.ER0001.getCodigo());
    	}
    	
    }

	@Override
	@Transactional
    public PessoaDto atualizarCliente(PessoaDto pessoaDto) throws Exception {
        Pessoa pessoa = pessoaMapper.retornaPessoaEntity(pessoaDto);
        Endereco endereco = enderecoMapper.retornaEnderecoEntity(pessoaDto.getEndereco(), pessoaDto);

        this.validarRegrasAlterar(pessoaDto);
        
        if (!pessoaDto.getNome().isEmpty()) {
        	
        	this.atualizarPessoa(pessoa);
        	
        	endereco.setPessoa(pessoa);
        	this.atualizarEndereco(endereco);
        	
        	return pessoaMapper.retornaPessoaDto(pessoa);
        }
        
        throw new Exception(ErroEnum.ER0003.getMensagem());
    }

	
	@Transactional(TxType.REQUIRES_NEW)
    private Pessoa atualizarPessoa(Pessoa pessoa) {
        return clienteDao.atualizar(pessoa);
    }

    @Transactional(TxType.REQUIRES_NEW)
    private Endereco atualizarEndereco(Endereco endereco) {
        return enderecoDao.atualizar(endereco);
    }
   
	@Override
	@Transactional
	public Boolean removerCliente(PessoaDto pessoaDto) throws Exception {
		Pessoa pessoa = pessoaMapper.retornaPessoaEntity(pessoaDto);
		Endereco endereco = enderecoMapper.retornaEnderecoEntity(pessoaDto.getEndereco(), pessoaDto);


		if (!pessoaDto.getNome().isEmpty()) {

			
			clienteDao.remover(pessoa);
			//enderecoDao.remover(endereco);

			return true;
		}

		throw new Exception(ErroEnum.ER0003.getMensagem());
	}

	

	@Override
	public List<PessoaDto> consultarCliente(PessoaDto pessoaDto) {
		
		List<Pessoa> listaBanco = clienteDao.buscarPessoas(pessoaDto);
		List<PessoaDto> listaResultado = new ArrayList<>();
		
		for (Pessoa entidade : listaBanco) {
			listaResultado.add(pessoaMapper.retornaPessoaDto(entidade, entidade.getEnderecos().get(0)));
		}
		
		return listaResultado;
	}
}
