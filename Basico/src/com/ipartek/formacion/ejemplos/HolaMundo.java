package com.ipartek.formacion.ejemplos;

import java.util.Scanner;

/**
 * Clase que representa el propio programa
 */
public class HolaMundo {
	/**
	 * Método que se ejecuta al iniciar un programa Java
	 * 
	 * @param args Argumentos recibidos por consola
	 */
	public static void main(String[] args) {
		/*
		 * Programa de ejemplo de Java Muestra las capacidades básicas
		 */

		System.out.println("Hola"); // Mostrar Hola por consola

		int i = 5;

		long l = 2148000000l;

		float f = 5.6f;

		boolean b = true;

		char c;

		c = 'a';

		System.out.println(i);
		System.out.println(l);
		System.out.println(f);
		System.out.println(b);
		System.out.println(c);
		
		String nombre = "Javier";
		String apellidos;
		
		apellidos = "Lete";
		
		System.out.println(nombre + " " + apellidos);
		
		System.out.println(nombre.toUpperCase());
		
		System.out.println(nombre.indexOf("avi"));
		
		String nombreAbreviado = nombre.substring(0, 4);

		System.out.println(nombreAbreviado);
		
		// "Como preguntar si tienes dos nombres pero eres la misma persona"
		System.out.println(nombre == "Javier"); // Son EL MISMO objeto
		
		System.out.println(nombreAbreviado == "Javi"); // NO son el mismo objeto
		
		// "Como preguntar si son gemelos"
		System.out.println(nombreAbreviado.equals("Javi"));	// Comparación de objetos por contenido
		
		nombre = nombreAbreviado;
		
		System.out.println(nombre.length());
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("¿Cuántos alumnos tienes? ");
		
		int numeroAlumnos = sc.nextInt();
		sc.nextLine();
		
		System.out.println(numeroAlumnos);
		
		System.out.println(numeroAlumnos > 20);
		
		System.out.print("Dime tu nombre: ");
		
		nombre = sc.nextLine();
		
		System.out.println("Hola " + nombre);
		
		sc.close();
	}
}
