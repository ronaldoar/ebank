package br.com.csoft.ebank.repository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.csoft.ebank.model.AbstractEntity;
import br.com.csoft.ebank.repository.commons.GenericRepository;



/**
 * Classe <code>Repository.</code><br/>
 * Define operacoes crud no database de forma generica 
 * para todas as entidades que extendem AbstractEntity
 * 
 * @author ronaldo.rios
 * @since 2013-08-07
 * @version 1.0.0
 * 
 * @see AbstractEntity
 * @see GenericRepository
 * @see EntityManager
 * 
 * @param <T> - AbstractEntity
 */
public class Repository<T extends AbstractEntity> implements GenericRepository<T>{
	
	protected EntityManager manager;
	protected final Class<T> clazz;
	
	public Repository(EntityManager manager){
		this.manager = manager;
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.clazz = clazz;
	}
	
	@Override
	public void save(T entity) {
		manager.persist(entity);
	}

	@Override
	public T saveAndReturn(T entity) {
		manager.persist(entity);
		manager.refresh(entity);
		return entity;
	}
	
	@Override
	public void remove(Long id){
		manager.remove(manager.getReference(clazz, id));
	}
	
	@Override
	public void remove(Integer id){
		manager.remove(manager.getReference(clazz, id));
	}
	
	@Override
	public void remove(String id){
		manager.remove(manager.getReference(clazz, id));
	}
	
	@Override
	public void update(T entity) {
		manager.merge(entity);
		manager.flush();
	}

	@Override
	public Collection<T> loadAll() {
		Query query = manager.createQuery("from " + clazz.getName());
		@SuppressWarnings("unchecked")
		Collection<T> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public T loadById(Long id) {
		return manager.find(clazz, id);
	}
	
	@Override
	public T loadById(Integer id) {
		return manager.find(clazz, id);
	}
	
	@Override
	public T loadById(String id) {
		return manager.find(clazz, id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public T loadById(Enum id) {
		return manager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadByField(String field, String value) {
		List<T>list = new ArrayList<T>();
		String jpql = "select o from "+ clazz.getName() + " o where o." + field + " = :value";
		list = manager.createQuery(jpql).setParameter("value", value).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadByField(String field, Integer value) {
		List<T>list = new ArrayList<T>();
		String jpql = "select o from "+ clazz.getName() + " o where o." + field + " = :value";
		list = manager.createQuery(jpql).setParameter("value", value).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadByField(String field, boolean value) {
		List<T>list = new ArrayList<T>();
		String jpql = "select o from "+ clazz.getName() + " o where o." + field + " = :value";
		list = manager.createQuery(jpql).setParameter("value", value).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadByField(String field, Long value) {
		List<T>list = new ArrayList<T>();
		String jpql = "select o from "+ clazz.getName() + " o where o." + field + " = :value";
		list = manager.createQuery(jpql).setParameter("value", value).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> loadByFields(Map<String, Object> filtros){
		List<T>list = new ArrayList<T>();
		StringBuilder sql = montaSql(filtros);
		
		list = manager.createQuery(sql.toString()).getResultList();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> loadByFields(Map<String, Object> filtros, String orderBy){
		List<T>list = new ArrayList<T>();
		StringBuilder sql = montaSql(filtros);
		
		sql.append(" ORDER BY ").append(orderBy);
		list = manager.createQuery(sql.toString()).getResultList();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> loadByFields(Map<String, Object> filtros, String orderBy, String groupBy){
		List<T>list = new ArrayList<T>();
		StringBuilder sql = montaSql(filtros);
		
		sql.append(" GROUP BY ").append(groupBy);
		
		if( orderBy != null ){
			sql.append(" ORDER BY ").append(orderBy);
		}
		list = manager.createQuery(sql.toString()).getResultList();
		
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StringBuilder montaSql(Map<String, Object> filtros){
		StringBuilder sql = new StringBuilder();
		sql.append("FROM ").append(clazz.getName()).append(" ");
		
		boolean where = false;
		if(filtros != null && filtros.size() > 0){
			Iterator<Entry<String, Object>> iterator = filtros.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, Object> next = iterator.next();
				if(where){
					sql.append(" AND ");
				} else {
					sql.append(" WHERE ");
					where = true;
				}
				Object valor = next.getValue();
				String campo = next.getKey();
				
				String sinal = "=";
				if( valor instanceof List ){
					sinal = ((List)valor).get(0).toString();
					valor = ((List)valor).get(1);
				}
				if( valor instanceof String ){
					String valorAux = valor.toString();
					if( valorAux.startsWith("%") || valorAux.endsWith("%") ){
						sql.append(campo).append(" like '").append(valorAux).append("'");
					}
					else{
						sql.append(campo).append(" ").append(sinal).append(" '").append(valor).append("'");
					}
				}
				else{
					if( valor instanceof Enum ){
						sql.append(campo).append(" ").append(sinal).append(" ").append(valor.getClass().getName()).append(".").append(valor);
					}
					else{
						if( valor instanceof Calendar || valor instanceof Date ){
							sql.append("trunc("+campo+")").append(" = to_date('").append(valor).append("', 'dd/mm/yyyy HH24:MI:ss')");
						}
						else{
							if(valor instanceof List){
								sql.append(campo).append(" ").append(sinal).append(" (").append(obtemStringIn(((List<Object>)valor))).append(") ");
							}
							else{
								sql.append(campo).append(" ").append(sinal).append(" ").append(valor);
							}
						}
					}
				}
				
			}
		}
		
		return sql;
	}
	
	public String obtemStringIn(List<Object> valores){
		String result = "";
		for( Object valor : valores ){
			if( valor instanceof String ){
				result += "'" + valor + "'";
			}
			else{
				if( valor instanceof Enum ){
					result += valor.getClass().getName() + "." + valor;
				}
				else{
					if( valor instanceof Calendar || valor instanceof Date ){
						result += "to_date('" + valor + "', 'dd/mm/yyyy HH24:MI:ss')";
					}
					else{
						result += valor;
					}
				}
			}
			result += ", ";
		}
		
		if( result.endsWith(", ") ){
			result = result.substring(0, result.length()-2);
		}
		
		return result;
	}
	
	/**
	 * Verifica se ja existe um id para a entidade classificando
	 * a transacao como um update.
	 * @param id - key da entidade no database
	 * @return boolean - true para update.
	 */
	@Override
	public boolean isUpdate(Long id){
		
		if(id != null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Retorna a key de maior valor de uma determinada entidade.
	 * @return Long - valore da key.
	 */
	@Override
	public Long maxKey()throws PersistenceException {
		String jpql = "select max(o.id) from "+ clazz.getName()+" o";
		Object number = manager.createQuery(jpql).getSingleResult();
		if(number != null){
			return (Long)number;
		}else{
			return 0l;
		}
	}
	
	/**
	 * Conta a quantidade de registros
	 * @return
	 * @throws PersistenceException
	 */
	public int count()throws PersistenceException {
		String jpql = "select count(o.id) FROM "+ clazz.getName() +" o";
		Object number = manager.createQuery(jpql).getSingleResult();
		if(number != null){
			return (Integer)number;
		}else{
			return 0;
		}
	}
	
	/**
	 * Conta a quantidade de registros 
	 * @param field - campo da tabela
	 * @param value - valor do campo
	 * @return
	 * @throws PersistenceException
	 */
	public long countByField(String field, Long value)throws PersistenceException {
		String jpql = "select count(o.id) FROM "+ clazz.getName() +" o WHERE o."+field +" = :value";
		Object number = manager.createQuery(jpql).setParameter("value", value).getSingleResult();
		if(number != null){
			return (Long)number;
		}else{
			return 0l;
		}
	}
	
	/**
	 * Conta a quantidade de registros 
	 * @param field - campo da tabela
	 * @param value - valor do campo
	 * @return
	 * @throws PersistenceException
	 */
	public long countByField(String field, String value)throws PersistenceException {
		String jpql = "select count(o.id) FROM "+ clazz.getName() +" o WHERE o."+field +" = :value";
		Object number = manager.createQuery(jpql).setParameter("value", value).getSingleResult();
		if(number != null){
			return (Long)number;
		}else{
			return 0l;
		}
	}
}
