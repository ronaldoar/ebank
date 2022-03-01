package br.com.csoft.ebank.util;

public class Tmp {
	
	public static void main(String[] args) {
		String pass = "12345678";
		String cripto = new CriptoUtils(pass).getEncriptedPassword();
		System.out.println(cripto);
	}
}
