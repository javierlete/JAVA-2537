package com.ipartek.formacion.ejemplos.aplicacion;

import java.time.LocalDate;

import com.ipartek.formacion.ejemplos.poo.Contacto;
import com.ipartek.formacion.ejemplos.poo.Oficina;

import static com.ipartek.formacion.ejemplos.bibliotecas.Consola.*;

public class MantenimientoOficina {

	private static final int LISTADO = 1;
	private static final int BUSCAR = 2;
	private static final int INSERTAR = 3;
	private static final int MODIFICAR = 4;
	private static final int BORRAR = 5;
	
	private static final int SALIR = 0;

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
		pl("""
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
		return rInt("Dime la opción que deseas");
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
		pl("LISTADO DE CONTACTOS");

		for(Contacto c: OFICINA.getEmpleados()) {
			// TODO Mejorar formato de visualización
			pl(c);
		}
	}

	private static void buscar() {
		pl("BUSCAR POR ID");
	
		Long id = rLong("ID");
		
		Contacto contacto = OFICINA.buscarEmpleadoPorId(id);
		
		pl(contacto);
		
		// TODO Mejorar la visualización del contacto en formato ficha
	}

	private static void insertar() {
		pl("INSERTAR");
		
		Contacto contacto = new Contacto();
		
		pedirDatosContacto(contacto);
		
		OFICINA.contratar(contacto);
		
		listado();
	}

	private static void modificar() {
		pl("MODIFICAR");
		
		Long id = rLong("ID");
		
		Contacto contacto = OFICINA.buscarEmpleadoPorId(id);
		
		pedirDatosContacto(contacto);
		
		listado();
	}

	private static void pedirDatosContacto(Contacto contacto) {
		contacto.setNombre(rString("Nombre"));
		contacto.setApellidos(rString("Apellidos"));
		
		String sFecha = rString("Fecha de nacimiento (AAAA-MM-DD)");
		LocalDate fechaNacimiento = LocalDate.parse(sFecha);
		
		contacto.setFechaNacimiento(fechaNacimiento);
	}

	private static void borrar() {
		pl("BORRAR");
		
		Long id = rLong("ID");
		
		OFICINA.despedir(id);
		
		listado();
	}

	private static void salir() {
		pl("SALIENDO");
	}

	private static void opcionNoEncontrada() {
		pl("Opción no encontrada");
	}
}
