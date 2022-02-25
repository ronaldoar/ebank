package br.com.csoft.ebank.controller;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;
import br.com.csoft.ebank.repository.UsuarioRepository;

@Model
public class LoginController {
	
	@Inject
	@Default
	private EntityManager connection;
	
    @Inject
    private FacesContext facesContext;

	private String login;
	private String password;
	
	
	public String doLogin() {
		Usuario u = new UsuarioRepository(connection).buscarPeloNome(login);
		
		if(u == null) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario nao encontrado!", "Usuario nao encontrado.");
            facesContext.addMessage(null, m);
			return "/index.xhtml";
		}else {
			return "/views/contas.xhtml";
		}
		
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
