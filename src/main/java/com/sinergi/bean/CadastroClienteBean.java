package com.sinergi.bean;


import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import javax.inject.Inject;
import javax.inject.Named;

import com.sinergi.dto.PessoaDto;
import com.sinergi.exception.GenericaException;
import com.sinergi.service.ClienteService;


@Named("cadastroClienteBean")
@RequestScoped
public class CadastroClienteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PessoaDto pessoa;
	private Map<String, String> mapEstado;

	@Inject
	private ClienteService clienteService;

	@PostConstruct
    public void init() {
       if ( this.pessoa == null) {
    	   this.pessoa= new PessoaDto();
       }
       
       this.popularMapEstados();
    }

	public String cadastrar() {
		try {
			PessoaDto clienteSalvo = clienteService.salvarCliente(this.pessoa);
			
			String msg = String.format("Cliente %s cadastrado", clienteSalvo.getNome());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Cliente cadastrado"));
			
			this.limparCampos();
			
		} catch (GenericaException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "Houve uma validação ao tentar cadastrar o cliente"));
		
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Houve um erro ao tentar cadastrar o cliente"));
		}
		return "";
	}
	
	public String limparCampos() {
		this.pessoa = new PessoaDto(); 
		return "";
	}
	
	private void popularMapEstados() {
	   this.mapEstado = new TreeMap<>();
	   
	   this.mapEstado.put("AC", "Acre");
	   this.mapEstado.put("AL", "Alagoas");
	   this.mapEstado.put("AP", "Amapá");
	   this.mapEstado.put("AM", "Amazonas");
	   this.mapEstado.put("BA", "Bahia");
	   this.mapEstado.put("CE", "Ceará");
	   this.mapEstado.put("DF", "Distrito Federal");
	   this.mapEstado.put("ES", "Espírito Santo");
	   this.mapEstado.put("GO", "Goiás");
	   this.mapEstado.put("MA", "Maranhão");
	   this.mapEstado.put("MT", "Mato Grosso");
	   this.mapEstado.put("MS", "Mato Grosso do Sul");
	   this.mapEstado.put("MG", "Minas Gerais");
	   this.mapEstado.put("PA", "Pará");
	   this.mapEstado.put("PB", "Paraíba");
	   this.mapEstado.put("PR", "Paraná");
	   this.mapEstado.put("PE", "Pernambuco");
	   this.mapEstado.put("PI", "Piauí");
	   this.mapEstado.put("RJ", "Rio de Janeiro");
	   this.mapEstado.put("RN", "Rio Grande do Norte");
	   this.mapEstado.put("RS", "Rio Grande do Sul");
	   this.mapEstado.put("RO", "Rondônia");
	   this.mapEstado.put("RR", "Roraima");
	   this.mapEstado.put("SC", "Santa Catarina");
	   this.mapEstado.put("SP", "São Paulo");
	   this.mapEstado.put("SE", "Sergipe");
	   this.mapEstado.put("TO", "Tocantins");
	}
	
	public PessoaDto getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}

	public Map<String, String> getMapEstado() {
		return mapEstado;
	}

	public void setMapEstado(Map<String, String> mapEstado) {
		this.mapEstado = mapEstado;
	}
}
