package br.com.csoft.ebank.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe <code>CriptoUtils</code><br/>
 * Efetua a criptografia da senha do usuario.
 * 
 * @author ronaldo.rios
 * @since 2013-08-20
 * @version 1.0.0
 * 
 * @see MessageDigest
 */
public class CriptoUtils {
	
	public static final String MD5 = "md5";
	private String encriptedPassword;
	private static final String hexDigits = "0123456789abcdef";
	
	public CriptoUtils(String password){
		
		byte[] bytesEncripteds = null;
		try {
			bytesEncripteds = digest(password.getBytes(), MD5);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		encriptedPassword = byteArrayToHexString(bytesEncripteds);
	}
	
     /** 
     * Realiza um digest em um array de bytes atrav�s do algoritmo especificado 
     * @param input - O array de bytes a ser criptografado 
     * @param algoritmo - O algoritmo a ser utilizado 
     * @return byte[] - O resultado da criptografia 
     * @throws NoSuchAlgorithmException - Caso o algoritmo fornecido nao seja 
     * valido 
     */  
	public static byte[] digest(byte[]input, String algoritmo)throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance(algoritmo);
		md.reset();
		return md.digest(input);
	}
	
	 /** 
	  * Converte o array de bytes em uma representa��o hexadecimal. 
	  * @param input - O array de bytes a ser convertido. 
	  * @return Uma String com a representacao hexa do array 
	  */  
	public static String byteArrayToHexString(byte[]bytes){
		StringBuffer buffer = new StringBuffer();
		
		for(int i=0; i<bytes.length; i++){
			int j = ((int)bytes[i]) & 0xFF;
			buffer.append(hexDigits.charAt(j / 16));
			buffer.append(hexDigits.charAt(j % 16));
		}
		return buffer.toString();
	}
	
     /** 
      * Converte uma String hexa no array de bytes correspondente. 
      * @param hexa - A String hexa 
      * @return O vetor de bytes 
      * @throws IllegalArgumentException - Caso a String nao seja uma 
      * representacao haxadecimal valida 
      */ 
	public static byte[] hexStringToByteArray(String hexa)throws IllegalArgumentException {
		
		if(hexa.length() % 2 != 0){
			 throw new IllegalArgumentException("String hexa invalida");  
		}
		
		//verifica se a String possui uma quantidade par de elementos  
		byte[] bytes = new byte[hexa.length() / 2];
		
		for(int i=0; i<hexa.length(); i+=2){
			bytes[i / 2] = (byte)((hexDigits.indexOf(hexa.charAt(i)) << 4 ) | (hexDigits.indexOf(hexa.charAt(i + 1)))); 
		}
		return bytes;
	}

	public String getEncriptedPassword() {
		return encriptedPassword;
	}

}

