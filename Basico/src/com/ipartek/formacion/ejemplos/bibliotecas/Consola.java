package com.ipartek.formacion.ejemplos.bibliotecas;

import java.util.Scanner;

public class Consola {
	private static final Scanner SC = new Scanner(System.in);
	
	public static void pl(Object o) {
		System.out.println(o);
	}
	
	public static void p(Object o) {
		System.out.print(o);
	}
	
	public static String rString(String mensaje) {
		p(mensaje + ": ");
		return SC.nextLine();
	}
	
	public static int rInt(String mensaje) {
		String texto = rString(mensaje);
		return Integer.parseInt(texto);
	}
	
	public static long rLong(String mensaje) {
		String texto = rString(mensaje);
		return Long.parseLong(texto);
	}
	
	
}
