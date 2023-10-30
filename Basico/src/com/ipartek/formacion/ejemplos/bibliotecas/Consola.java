package com.ipartek.formacion.ejemplos.bibliotecas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
	
	public static Integer rInt(String mensaje) {
		int i;
		String texto;
		boolean hayError = true;
		
		do {
			texto = rString(mensaje);
			try {
				i = Integer.parseInt(texto);
				return i;
			} catch (NumberFormatException e) {
				pl("Debes introducir un número");
			} 
		} while (hayError);
		
		return null;
	}
	
	public static Long rLong(String mensaje) {
		Long l;
		String texto;
		boolean hayError = true;
		
		do {
			texto = rString(mensaje);
			try {
				l = Long.parseLong(texto);
				return l;
			} catch (NumberFormatException e) {
				pl("Debes introducir un número");
			} 
		} while (hayError);
		
		return null;
	}
	
	public static LocalDate rLocalDate(String mensaje) {
		LocalDate ld;
		String texto;
		boolean hayError = true;
		
		do {
			texto = rString(mensaje + " (AAAA-MM-DD)");
			try {
				ld = LocalDate.parse(texto);
				return ld;
			} catch (DateTimeParseException e) {
				pl("Debes introducir una fecha en formato AAAA-MM-DD");
			} 
		} while (hayError);
		
		return null;
	}
	
	
}
