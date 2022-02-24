package br.com.csoft.ebank.util;

import java.util.regex.Pattern;


/**
 * Classe <code>StringUtils</code><br/>
 * 
 * Define classe de utilitarios de manipulacao de Strings
 * @author ronaldo.rios.ext
 * @see Pattern
 * @since 2014-09-10
 *
 */

public class StringUtils {
	
	/**
	 * Retorna true se a string for null
	 * @param str
	 * @return
	 */
	public static boolean isNull(final String str) {
		if (str == null) return true;
		if (str.length() == 0) return true;
		if (str.equalsIgnoreCase("null")) return true;
		if (str.trim().equals(""))return true;
		return false;
	}
	
	/**
	 * Retorna true se a string nao for null ou 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(final String str) {
		return !isNull(str);
	}
	

	/**
	 * Remove espacos em branco no inicio da string
	 * @param str
	 * @return
	 */
	public static String initTrim(String str) {
	    return str.replaceAll("^\\s+", "");
	}
	 
	/**
	 * Remove espacos em branco no fim da string
	 * @param testeTrim
	 * @return
	 */
	public static String endTrim(String str) {
	    return str.replaceAll("\\s+$", "");
	}
	
	/**
	 * Remove espacos em branco no inicio 
	 * e no fim da string
	 * @param str
	 * @return
	 */
	public static String initEndTrim(String str) {
		 String init =  str.replaceAll("^\\s+", "");
		 String end  =  init.replaceAll("\\s+$", "");
		 return end;
	}
	
	/**
	 * Verifica se a string tem caracteres especiais
	 * @param value
	 * @return flag 
	 */
	public static boolean hasSpecialChar(String value){
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		return p.matcher(value).find();
	}
}
