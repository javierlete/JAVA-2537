package com.ipartek.formacion.ejemplos.aplicacion;

import java.util.Scanner;

public class MantenimientoOficina {

	private static final int LISTADO = 1;
	private static final int BUSCAR = 2;
	private static final int INSERTAR = 3;
	private static final int MODIFICAR = 4;
	private static final int BORRAR = 5;
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
		System.out.println("""
				MENU
				====

				1. Listado
				2. Buscar por id
				3. Insertar
				4. Modificar
				5. Borrar

				0. Salir
				""");
	}

	private static int recibirOpcion() {
		System.out.print("Dime la opción que deseas: ");
		return SC.nextInt();
	}

	private static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case LISTADO:
			listado();
			break;
		case BUSCAR:
			buscar();
			break;
		case INSERTAR:
			insertar();
			break;
		case MODIFICAR:
			modificar();
			break;
		case BORRAR:
			borrar();
			break;
		case SALIR:
			salir();
			break;
		default:
			opcionNoEncontrada();
		}
	}

	private static void listado() {
		System.out.println("LISTADO DE CONTACTOS");

		// TODO Completar el listado de oficinas
	}

	private static void buscar() {
		System.out.println("BUSCAR POR ID");

		// TODO Completar el buscar por id
	}

	private static void insertar() {
		System.out.println("INSERTAR");
		
		// TODO Completar el insertar
	}

	private static void modificar() {
		System.out.println("MODIFICAR");
		
		// TODO Completar el modificar
	}

	private static void borrar() {
		System.out.println("BORRAR");
		
		// TODO Completar el borrar
	}

	private static void salir() {
		System.out.println("SALIENDO");
	}

	private static void opcionNoEncontrada() {
		System.out.println("Opción no encontrada");
	}
}
