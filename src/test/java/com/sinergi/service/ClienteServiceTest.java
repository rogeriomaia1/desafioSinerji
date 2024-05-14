package com.sinergi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sinergi.dao.ClienteDao;
import com.sinergi.dao.EnderecoDao;
import com.sinergi.dto.EnderecoDto;
import com.sinergi.dto.PessoaDto;
import com.sinergi.enums.ErroEnum;
import com.sinergi.exception.GenericaException;
import com.sinergi.mapper.EnderecoMapper;
import com.sinergi.mapper.PessoaMapper;
import com.sinergi.model.Endereco;
import com.sinergi.model.Pessoa;
import com.sinergi.service.impl.ClienteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @Mock
    private ClienteDao clienteDao;

    @Mock
    private EnderecoDao enderecoDao;

    @Mock
    private PessoaMapper pessoaMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private PessoaDto pessoaDto;
    private Pessoa pessoa;
    private Endereco endereco;
    
    @Before
    public void setUp() {
        pessoaDto = new PessoaDto();
        pessoaDto.setNome("Nicolas Cage");
        pessoaDto.setEndereco(new EnderecoDto(
        						null,
        						"Tocantins", 
        						"Jericaçu", 
        						"Rua A", 
        						"10A", 
        						"123456"
        						));
        
        pessoa = new Pessoa();
        pessoa.setNome("Nicolas Cage");
        
        endereco = new Endereco(
		        		"Tocantins", 
						"Jericaçu", 
						"Rua A", 
						"10A", 
						"123456",
		                pessoa);
    }

    @After
    public void tearDown() {
        pessoaDto = null;
    }

    @Test
    @Transactional
    public void testSalvarCliente() throws Exception {
        when(pessoaMapper.retornaPessoaEntity(pessoaDto)).thenReturn(new Pessoa());
        when(enderecoMapper.retornaEnderecoEntity(pessoaDto.getEndereco(), pessoaDto)).thenReturn(new Endereco());
        when(pessoaMapper.retornaPessoaDto(any())).thenReturn(pessoaDto);
        
        
        assertEquals("Nicolas Cage", clienteService.salvarCliente(pessoaDto).getNome());
    }

   
    public void testAtualizarClienteComNomeExistente() throws Exception {
        when(pessoaMapper.retornaPessoaEntity(pessoaDto)).thenReturn(pessoa);
        when(enderecoMapper.retornaEnderecoEntity(any(), any())).thenReturn(endereco);
        when(clienteDao.buscarPessoaPorNome("Nicolas Cage")).thenReturn(pessoa);
        try {
            clienteService.atualizarCliente(pessoaDto);
        } catch (Exception e) {
            assertEquals(ErroEnum.ER0001.getMensagem(), e.getMessage());
        }
    }


    @Test(expected = Exception.class)
    public void testSalvarClienteComNomeVazio() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("");

        clienteService.salvarCliente(pessoaDto);
    }

    @Test(expected = GenericaException.class)
    public void testSalvarClienteComNomeExistente() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Nicolas Cage");

        when(clienteDao.buscarPessoaPorNome("Nicolas Cage")).thenReturn(new Pessoa());

        clienteService.salvarCliente(pessoaDto);
    }

    @Test
    public void testAtualizarCliente() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Nicolas Cage");
        pessoaDto.setEndereco(new EnderecoDto());

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);

        when(pessoaMapper.retornaPessoaEntity(pessoaDto)).thenReturn(pessoa);
        when(enderecoMapper.retornaEnderecoEntity(pessoaDto.getEndereco(), pessoaDto)).thenReturn(endereco);

        clienteService.atualizarCliente(pessoaDto);

        verify(clienteDao, times(1)).atualizar(pessoa);
        verify(enderecoDao, times(1)).atualizar(endereco);
    }

    @Test(expected = Exception.class)
    public void testAtualizarClienteComNomeVazio() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("");

        clienteService.atualizarCliente(pessoaDto);
    }

    @Test
    public void testRemoverCliente() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Nicolas Cage");

        assertTrue(clienteService.removerCliente(pessoaDto));
    }

    @Test(expected = Exception.class)
    public void testRemoverClienteComNomeVazio() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("");

        clienteService.removerCliente(pessoaDto);
    }

    @Test
    public void testConsultarCliente() {
    	
    	Pessoa p = new Pessoa();
        p.setNome("Nicolas Cage");
        p.getEnderecos().add(endereco);
        
        List<Pessoa> listaBanco = new ArrayList<>();
        p.getEnderecos().add(endereco);
        listaBanco.add(p);

        when(clienteDao.buscarPessoas(any())).thenReturn(listaBanco);
        when(pessoaMapper.retornaPessoaDto(any(), any())).thenReturn(this.pessoaDto);

        List<PessoaDto> listaResultado = clienteService.consultarCliente(pessoaDto);

        assertNotNull(listaResultado);
        assertFalse(listaResultado.isEmpty());
        assertEquals(1, listaResultado.size());
    }
}
