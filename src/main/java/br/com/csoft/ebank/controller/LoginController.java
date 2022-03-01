package br.com.csoft.ebank.controller;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;
import br.com.csoft.ebank.repository.UsuarioRepository;
import br.com.csoft.ebank.util.StringUtils;

@Model
public class LoginController extends DefaultController {
	private static final long serialVersionUID = 1L;

	@Inject
	@Default
	private EntityManager connection;
	
    private String msg;
	private String login;
	private String password;
	
	
	public String doLogin() {
		
		if(StringUtils.isNotNull(login) && StringUtils.isNotNull(password)) {

			Usuario u = new UsuarioRepository(connection).login(login, password);
			this.setUserCommon(u);
			
			if(u == null) {
				this.msg = "Acesso negado!";
				return "/index.xhtml";
			}else {
				this.setUserCommon(u);
				return "/views/contas.xhtml";
			}
		}else {
			msg = "Informe o usuário e a senha";
			return null;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
