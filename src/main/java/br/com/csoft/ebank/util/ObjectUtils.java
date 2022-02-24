package br.com.csoft.ebank.util;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe <code>ObjectUtils</code><br/>
 * Define classe de utilitarios de objetos
 * e tipos Java.
 * 
 * @author ronaldo.rios.ext
 * @since 2014-09-10
 * @version 1.0.0
 *
 */
public class ObjectUtils {
	
	
	/**
	 * Verifica se uma lista esta vazia ou nula.
	 * @param list - lista de objetos
	 * @return - flag 
	 */
	public static boolean isEmpty(List<?>list){
		if(list == null || list.size() <= 0){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Verifica se uma lista n�o esta vazia ou nula.
	 * @param list - lista de objetos
	 * @return - flag true para lista vazia
	 */
	public static boolean isNotEmpty(List<?>list){
		return !isEmpty(list);
	}
	
	/**
	 * Remove lista da posicao informada
	 * @param list - lista de 
	 * @param pos - posicao (index) a ser removida
	 * @return
	 */
	public static List<?> delete(List<?>list, int pos){
		
		int fim = list.size()-1;
		
		for(int i=fim; i>=0; i--){
			if(i == pos){
				list.remove(i);
			}
		}
		return list;
	}
	
	/**
	 * Verifica se a string representa um numero
	 * @param number
	 * @return
	 */
	public static boolean isNumber(String number){
		return (number.matches("\\d*"));
	}
	
	/**
	 * retorna flag true para vazio
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(int[]array){
		return (array != null && array.length > 0) ? false : true;
	}
	
	/**
	 * retorna flag true para nao vazio
	 * @param array
	 * @return
	 */
	public static boolean isNotEmpty(int[]array){
		return (array != null && array.length > 0) ? true : false;
	}
	
	/**
	 * Retorna flag para valores nao null e maior que zero.
	 * @param value
	 * @return
	 */
	public static boolean hasValue(Integer value){
		return value != null && value > 0;
	}
	
	/**
	 *  Retorna flag para valores nao null e maior que zero.
	 * @param value
	 * @return
	 */
	public static boolean hasValue(Long value){
		return value != null && value > 0;
	}
	
	/**
	 * Efetua o parse da String para inteiro.
	 * @param value - valor
	 * @param zero  - flag para valores incluindo zero
	 * @return Integer
	 */
	public static Integer parseInteger(String value, boolean zero){
		return StringUtils.isNotNull(value) ? (zero ? Integer.valueOf(value) : (value.equals("0") ? null : Integer.valueOf(value)) ) : null;
	}
	
	/**
	 * Efetua o parse da String para Double.
	 * @param value - valor
	 * @param zero  - flag para valores incluindo zero
	 * @return Double
	 */
	public static Double parseDouble(String value, boolean zero){
		return StringUtils.isNotNull(value) ? (zero ? Double.valueOf(value) : (value.equals("0") ? null : Double.valueOf(value)) ) : null;
	}
	
	public static BigDecimal parseBigDec(String value, boolean zero){
		return StringUtils.isNotNull(value) ? (zero ? new BigDecimal(value) : (value.equals("0") ? null : new BigDecimal(value)) ) : null;
	}
	
	
	/**
	 * Obtem o Objeto informado ou retorna null caso o mesmo
	 * esteja null.
	 * @param instance
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObject(Object instance, Class<T> clazz) {
		if(instance == null)return null;
		
		if(instance instanceof String){
			return (T) (StringUtils.isNotNull(instance.toString()) ? instance.toString() : null);
		}else {
			return clazz.cast(instance);
		}
	}
	
	/**
	 * Remove separadores de numeros
	 * @param doc
	 * @return
	 */
	public static String separatorMaskRemove(String doc){
		return doc.replace("-", "");
	}

}
