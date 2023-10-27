package com.ipartek.formacion.ejemplos.aplicacion;

import java.time.LocalDate;
import java.util.Scanner;

import com.ipartek.formacion.ejemplos.poo.Contacto;
import com.ipartek.formacion.ejemplos.poo.Oficina;

public class MantenimientoOficina {

	private static final int LISTADO = 1;
	private static final int BUSCAR = 2;
	private static final int INSERTAR = 3;
	private static final int MODIFICAR = 4;
	private static final int BORRAR = 5;
	
	private static final int SALIR = 0;

	private static final Scanner SC = new Scanner(System.in);
	
	private static final Oficina OFICINA = new Oficina(null, "Bilbao", new Contacto("Javier"));
	
	static {
		OFICINA.contratar(new Contacto("Uno"));
		OFICINA.contratar(new Contacto("Dos"));
		OFICINA.contratar(new Contacto("Tres"));
		OFICINA.contratar(new Contacto("Cuatro"));
	}

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
		int opcion = SC.nextInt();
		SC.nextLine();
		
		return opcion;
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

		for(Contacto c: OFICINA.getEmpleados()) {
			// TODO Mejorar formato de visualización
			System.out.println(c);
		}
	}

	private static void buscar() {
		System.out.println("BUSCAR POR ID");
	
		System.out.print("ID: ");
		Long id = SC.nextLong();
		
		Contacto contacto = OFICINA.buscarEmpleadoPorId(id);
		
		System.out.println(contacto);
		
		// TODO Mejorar la visualización del contacto en formato ficha
	}

	private static void insertar() {
		System.out.println("INSERTAR");
		
		Contacto contacto = new Contacto();
		
		pedirDatosContacto(contacto);
		
		OFICINA.contratar(contacto);
		
		listado();
	}

	private static void modificar() {
		System.out.println("MODIFICAR");
		
		System.out.print("ID: ");
		Long id = SC.nextLong();
		SC.nextLine();
		
		Contacto contacto = OFICINA.buscarEmpleadoPorId(id);
		
		pedirDatosContacto(contacto);
		
		listado();
	}

	private static void pedirDatosContacto(Contacto contacto) {
		System.out.print("Nombre: ");
		contacto.setNombre(SC.nextLine());
		
		System.out.print("Apellidos: ");
		contacto.setApellidos(SC.nextLine());
		
		System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
		contacto.setFechaNacimiento(LocalDate.parse(SC.nextLine()));
	}

	private static void borrar() {
		System.out.println("BORRAR");
		
		System.out.print("ID: ");
		Long id = SC.nextLong();
		
		OFICINA.despedir(id);
		
		listado();
	}

	private static void salir() {
		System.out.println("SALIENDO");
	}

	private static void opcionNoEncontrada() {
		System.out.println("Opción no encontrada");
	}
}
