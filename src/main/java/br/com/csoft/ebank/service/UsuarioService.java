package br.com.csoft.ebank.service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;
import br.com.csoft.ebank.model.UsuarioPerfil;

@Stateless
public class UsuarioService {
	
    private Logger log = Logger.getLogger(UsuarioService.class.getName());

    @Inject
    private EntityManager em;
    
    public void cadastrar(Usuario usuario, UsuarioPerfil perfil)throws IllegalArgumentException, Exception {
    	verificarPermissao(perfil);
    	usuario.setDtCadastro(LocalDateTime.now());
    	usuario.setAtivo(true);
    	log.info("[CADASTRO] "+"cadastro de usuario efetuado.");
    	em.persist(usuario);
	}
    
    private void verificarPermissao(UsuarioPerfil perfil) {
    	if(perfil.ADMIN != UsuarioPerfil.ADMIN)throw new IllegalArgumentException("Necessita de permissão administrador");
    }
    
}
