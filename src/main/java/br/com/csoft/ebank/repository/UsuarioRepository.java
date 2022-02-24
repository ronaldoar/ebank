package br.com.csoft.ebank.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import br.com.csoft.ebank.model.Usuario;
import br.com.csoft.ebank.util.ObjectUtils;

@ApplicationScoped
public class UsuarioRepository extends Repository<Usuario>{
		
	public UsuarioRepository(EntityManager em) {
		super(em);
	}

	public List<Usuario> buscarTodosPorNome() {
		return (List<Usuario>) this.loadAll();
	}
	 
	public Usuario buscarPeloNome(String username) {
		return ObjectUtils.isEmpty(this.loadByField("username", username)) ? null : this.loadByField("username", username).get(0);
	}
}
