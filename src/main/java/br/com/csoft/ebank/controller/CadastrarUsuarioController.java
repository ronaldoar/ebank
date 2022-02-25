package br.com.csoft.ebank.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;
import br.com.csoft.ebank.repository.UsuarioRepository;
import br.com.csoft.ebank.service.UsuarioService;

@Model
public class CadastrarUsuarioController {
	
	@Inject
	@Default
	private EntityManager connection;
	
	@Inject
	private FacesContext facesContext;
	
	private String password;
	private Usuario usuario;
	private List<Usuario>usuarios;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
		usuarios = new UsuarioRepository(connection).buscarTodosPorNome();
	}
	
	public void cadastrar() {
		try {
			new UsuarioService().cadastrar(usuario, null);
			
		}catch(IllegalArgumentException ex) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null);
			facesContext.addMessage(null, m);
			
		}catch(Exception ex) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um problema ao tentar cadastrar o usuario!", null);
			facesContext.addMessage(null, m);
		}
	}
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
