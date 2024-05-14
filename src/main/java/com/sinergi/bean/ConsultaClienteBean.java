package com.sinergi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

import org.primefaces.event.RowEditEvent;

import com.sinergi.dto.PessoaDto;
import com.sinergi.exception.GenericaException;
import com.sinergi.service.ClienteService;

@Named("consultaClienteBean")
@SessionScoped
public class ConsultaClienteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<PessoaDto> listaPessoa;
	private Map<String, String> mapEstado;
	private PessoaDto filtroPessoa;
	private boolean condPrimeiroAcesso = true;
	

	@Inject
	private ClienteService clienteService;

	
	@PostConstruct
    public void init() {
		
		if (this.filtroPessoa == null) {
			this.filtroPessoa = new PessoaDto();
		}
		
		if (this.condPrimeiroAcesso) {
			this.consultar();
			this.condPrimeiroAcesso = false;
		}
		
		this.popularMapEstados();
    }
	
	public void limparCampos() {
		this.filtroPessoa.setNome("");
		this.filtroPessoa.setDataNascimento(null);
		this.filtroPessoa.setSexo("");
		
		this.consultar();
	}

	public String consultar() {
		
		this.listaPessoa = new ArrayList<PessoaDto>(); 
		listaPessoa = clienteService.consultarCliente(filtroPessoa);
		
		return "";
	}

	public void onRowEdit(RowEditEvent<PessoaDto> event) {
		
		PessoaDto pessoaEditada = (PessoaDto) event.getObject();
        
        try {
			PessoaDto clienteSalvo = clienteService.atualizarCliente(pessoaEditada);
			
			String msg = String.format("Cliente %s atualizado.", clienteSalvo.getNome());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Cliente atualizado"));
			
		} catch (GenericaException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "Houve uma validação ao tentar atualizar o cliente"));
		
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Houve um erro ao tentar atualizar o cliente"));
		} finally {
			this.consultar();
		}
		
    }

    public void onRowCancel(RowEditEvent event) {
    	//Se desejar alguma ação 
    }
    
    public void remover(PessoaDto pessoaEditada) {
    	try {
    		
    		Boolean foiRemovido = clienteService.removerCliente(pessoaEditada);
    		
    		if (foiRemovido) {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusão realizada", "Cliente removido"));
    			PrimeFaces.current().ajax().update(":formConsulta:listaCliente");
    		} else {
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Exclusão não realizada", "Cliente removido"));
    		}
    		
    	} catch (GenericaException e) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "Houve um erro ao tentar remover o cliente"));
    		
    	} catch (Exception e) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Houve um erro ao tentar remover o cliente"));
    	} finally {
    		this.consultar();
    	}
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

	public List<PessoaDto> getListaPessoa() {
		return listaPessoa;
	}
	
	public void setListaPessoa(List<PessoaDto> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}
	
	public PessoaDto getFiltroPessoa() {
		return filtroPessoa;
	}
	
	public void setFiltroPessoa(PessoaDto filtroPessoa) {
		this.filtroPessoa = filtroPessoa;
	}
	

	public Map<String, String> getMapEstado() {
		return mapEstado;
	}

	public void setMapEstado(Map<String, String> mapEstado) {
		this.mapEstado = mapEstado;
	}
	
}
