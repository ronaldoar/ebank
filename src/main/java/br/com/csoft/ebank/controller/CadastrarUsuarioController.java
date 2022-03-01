package br.com.csoft.ebank.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;
import br.com.csoft.ebank.repository.UsuarioRepository;

@Model
public class CadastrarUsuarioController extends DefaultController {
	private static final long serialVersionUID = 1L;

	@Inject
	@Default
	private EntityManager connection;
	
	private String msg;
	
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
			new UsuarioRepository(connection).cadastrar(usuario, null);
			this.msg = "Usuário cadastrado com sucesso.";
			
		}catch(IllegalArgumentException ex) {
			this.msg = ex.getMessage();
			
		}catch(Exception ex) {
			this.msg = ex.getMessage();
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
