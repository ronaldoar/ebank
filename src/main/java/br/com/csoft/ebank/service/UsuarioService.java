package br.com.csoft.ebank.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;

@Stateless
public class UsuarioService {
	
    private Logger log = Logger.getLogger(UsuarioService.class.getName());

    @Inject
    private EntityManager em;
    
    public void cadastrar(Usuario usuario)throws Exception {
    	log.info("[CADASTRO] "+"cadastro de usuario efetuado.");
    	em.persist(usuario);
	}
}
