package br.com.csoft.ebank.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;
import br.com.csoft.ebank.model.UsuarioPerfil;
import br.com.csoft.ebank.util.CriptoUtils;
import br.com.csoft.ebank.util.ObjectUtils;

@ApplicationScoped
public class UsuarioRepository extends Repository<Usuario>{
	private Logger log = Logger.getLogger(UsuarioRepository.class.getName());

		
	public UsuarioRepository(EntityManager em) {
		super(em);
	}

	public List<Usuario> buscarTodosPorNome() {
		return (List<Usuario>) this.loadAll();
	}
	 
	public Usuario buscarPeloNome(String username) {
		return ObjectUtils.isEmpty(this.loadByField("username", username)) ? null : this.loadByField("username", username).get(0);
	}
	
	/**
	 * Efetua login do usuario.
	 * @param login
	 * @param password
	 * @return <code>Usuario</code>
	 */
	public Usuario login(String login, String password){
		String pss = new CriptoUtils(password).getEncriptedPassword();
		
		Map<String,Object>filtros = new HashMap<String, Object>();
		filtros.put("username", login);
		filtros.put("password", pss);
		filtros.put("ativo", true);
		List<Usuario>usuarios = loadByFields(filtros);
		return ObjectUtils.isEmpty(usuarios) ? null : usuarios.get(0);
	}
	
	public void cadastrar(Usuario usuario, UsuarioPerfil perfil)throws IllegalArgumentException, Exception {
    	verificarPermissao(perfil);
    	usuario.setDtCadastro(LocalDateTime.now());
    	usuario.setAtivo(true);
    	log.info("[CADASTRO] "+"cadastro de usuario efetuado.");
    	this.save(usuario);
	}
    
    
    private void verificarPermissao(UsuarioPerfil perfil) {
    	if(perfil.ADMIN != UsuarioPerfil.ADMIN)throw new IllegalArgumentException("Necessita de permissão administrador");
    }
    
}
