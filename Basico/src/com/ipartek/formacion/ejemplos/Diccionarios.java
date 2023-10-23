package com.ipartek.formacion.ejemplos;

import java.util.HashMap;

public class Diccionarios {
	public static void main(String[] args) {
		var dic = new HashMap<String, String>();
		
		dic.put("casa", "house");
		dic.put("perro", "dog");
		
		System.out.println(dic.get("perro"));
		
		System.out.println("CLAVES");
		
		for(String clave: dic.keySet()) {
			System.out.println(clave);
		}
		
		System.out.println("VALORES");
		
		for(String valor: dic.values()) {
			System.out.println(valor);
		}
	}
}
