package com.ipartek.formacion.ejemplos.aplicacion;

import java.util.Scanner;

public class MantenimientoOficina {
	
	private static final int SALIR = 0;
	private static final Scanner SC = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opcion;
		
		do {
			mostrarMenu();
			opcion = recibirOpcion();
			ejecutarOpcion(opcion);
		} while (opcion != SALIR);

	}

	private static void mostrarMenu() {
		System.out.println("MENU");
	}

	private static int recibirOpcion() {
		System.out.print("Dime la opción que deseas: ");
		return SC.nextInt();
	}

	private static void ejecutarOpcion(int opcion) {
		System.out.println(opcion);
	}

}
