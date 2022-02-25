package br.com.csoft.ebank.repository.commons;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.com.csoft.ebank.model.AbstractEntity;


/**
 * Classe <code>GenericRepository</code>.<br/>
 * Define Objeto de repositorio de dados da aplicacao.
 * 
 * @author ronaldo.rios
 * @version 1.0.0
 * @since 2014-09-16
 * @version 1.0.0
 *
 * @param <T>
 */
public interface GenericRepository <T extends AbstractEntity> {
	
	public void save(T entity);
	
	public T saveAndReturn(T entity);
	
	public void remove(Long id);
	
	public void remove(String id);
	
	public void remove(Integer id);
	
	public void update(T entity);
	
	public Collection<T> loadAll(); 
	
	public T loadById(Long id);
	
	public T loadById(Integer id);
	
	public T loadById(String id);
	
	@SuppressWarnings("rawtypes")
	public T loadById(Enum id);
	
	public List<T> loadByField(String field, String value);
	
	public List<T> loadByField(String field, boolean value);
	
	public List<T> loadByField(String field, Long value);
		
	public List<T> loadByField(String field, Integer value);
	
	public List<T> loadByFields(Map<String, Object> filtros);
	
	public List<T> loadByFields(Map<String, Object> filtros, String orderBy);
	
	public List<T> loadByFields(Map<String, Object> filtros, String orderBy, String groupBy);
	
	public boolean isUpdate(Long id);
	
	public Long maxKey();
	
	
}
