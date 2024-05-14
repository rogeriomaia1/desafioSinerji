package com.sinergi.bean;

import javax.faces.application.FacesMessage;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final String PAGINA_CONSULTA= "cliente/consultaCliente";
	
	private String login;
	
	private String senha;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String realizarLogin() {
        if (this.login == null || this.login.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "Login não pode ser vazio"));
            return null;
        }
        if (this.senha == null || this.senha.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", "Senha não pode ser vazia"));
            return null;
        }

        return PAGINA_CONSULTA;
    }

}
