package br.com.csoft.ebank.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.csoft.ebank.model.Usuario;

public class DefaultController implements Serializable {
	
	public static final String USER_APP_COMMON = "user_login";
	private static final long serialVersionUID = 1L;
	
	/**
	 * Obtem a sessao do usuario
	 * @return
	 */
	protected HttpSession getSession(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return session;
	}
	
	/**
	 * Configura o usuario na sessao
	 * @param usuario
	 */
	protected void setUserCommon(Usuario usuario){
		HttpSession session = getSession();
		session.setAttribute(USER_APP_COMMON, usuario);
		
	}

	/**
	 * Obtem o usuario da sessao
	 * @param usuario
	 */
	protected Usuario getUserCommon(){
		HttpSession session = getSession();
		return (Usuario) session.getAttribute(USER_APP_COMMON);
		
	}
	
	protected String msgError(){
		ResourceBundle bundle = getMessageBundle();
		return bundle.getString("msg.global.error");
	}
	
	/**
	 * Obtem objeto de internalionalizacao
	 * @return ResourceBundle
	 */ 
	protected ResourceBundle getMessageBundle(){
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getResourceBundle(context, "bundle");
	}
}
