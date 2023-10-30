package com.ipartek.formacion.ejemplos.aplicacion;

import static com.ipartek.formacion.ejemplos.bibliotecas.Consola.pl;
import static com.ipartek.formacion.ejemplos.bibliotecas.Consola.rInt;
import static com.ipartek.formacion.ejemplos.bibliotecas.Consola.rLocalDate;
import static com.ipartek.formacion.ejemplos.bibliotecas.Consola.rLong;
import static com.ipartek.formacion.ejemplos.bibliotecas.Consola.rString;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

import com.ipartek.formacion.ejemplos.poo.Contacto;
import com.ipartek.formacion.ejemplos.poo.Oficina;

public class MantenimientoOficina {

	private static final String FICHERO_CONTACTOS = "contactos.dat";
	private static final String FICHERO_CONTACTOS_CSV = "contactos.csv";
	
	private static final int LISTADO = 1;
	private static final int BUSCAR = 2;
	private static final int INSERTAR = 3;
	private static final int MODIFICAR = 4;
	private static final int BORRAR = 5;
	private static final int EXPORTAR = 6;
	private static final int IMPORTAR = 7;
	private static final int GUARDAR = 8;
	private static final int CARGAR = 9;

	private static final int SALIR = 0;

	private static Oficina oficina = new Oficina(null, "Bilbao", new Contacto("Javier"));


//	static {
//		OFICINA.contratar(new Contacto("Uno"));
//		OFICINA.contratar(new Contacto("Dos"));
//		OFICINA.contratar(new Contacto("Tres"));
//		OFICINA.contratar(new Contacto("Cuatro"));
//	}

	public static void main(String[] args) {
		int opcion;

		cargar();
//		importar();
		
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
				6. Exportar CSV
				7. Importar CSV
				8. Guardar
				9. Cargar

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
		case EXPORTAR:
			exportar();
			break;
		case IMPORTAR:
			importar();
			break;
		case GUARDAR:
			guardar();
			break;
		case CARGAR:
			cargar();
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

		for (Contacto c : oficina.getEmpleados()) {
			// TODO Mejorar formato de visualización
			pl(c);
		}
	}

	private static void buscar() {
		pl("BUSCAR POR ID");

		Long id = rLong("ID");

		Contacto contacto = oficina.buscarEmpleadoPorId(id);

		if (contacto != null) {
			pl(contacto);

			// TODO Mejorar la visualización del contacto en formato ficha
		} else {
			pl("No se ha encontrado el contacto");
		}
	}

	private static void insertar() {
		pl("INSERTAR");

		Contacto contacto = new Contacto();

		pedirDatosContacto(contacto);

		oficina.contratar(contacto);

		listado();
	}

	private static void modificar() {
		pl("MODIFICAR");

		Long id = rLong("ID");

		Contacto contacto = oficina.buscarEmpleadoPorId(id);

		if (contacto != null) {
			pedirDatosContacto(contacto);

			listado();
		} else {
			pl("No se ha encontrado el contacto");
		}
	}

	private static void pedirDatosContacto(Contacto contacto) {
		boolean hayError = true;

		do {
			try {
				contacto.setNombre(rString("Nombre"));
				hayError = false;
			} catch (Exception e) {
				pl(e.getMessage());
			}
		} while (hayError);

		String apellidos = rString("Apellidos");

		if (apellidos.trim().length() == 0) {
			apellidos = null;
		}

		contacto.setApellidos(apellidos);

		hayError = true;

		LocalDate fechaNacimiento = null;

		do {
			try {
				fechaNacimiento = rLocalDate("Fecha de nacimiento");
				contacto.setFechaNacimiento(fechaNacimiento);
				hayError = false;
			} catch (Exception e) {
				pl(e.getMessage());
			}
		} while (hayError);
	}

	private static void borrar() {
		pl("BORRAR");

		Long id = rLong("ID");

		oficina.despedir(id);

		listado();
	}

	private static void exportar() {
		pl("EXPORTAR");

		try (FileWriter fw = new FileWriter(FICHERO_CONTACTOS_CSV);
				PrintWriter pw = new PrintWriter(fw)) {
			System.out.println("\"ID\";\"Nombre\";\"Apellidos\";\"FechaNacimiento\"");
			pw.print(
				"""
				"ID";"Nombre";"Apellidos";"FechaNacimiento"
				""");
			
			for (Contacto c : oficina.getEmpleados()) {
				System.out.printf("%s;%s;%s;%s\n", c.getId(), c.getNombre(), c.getApellidos(), c.getFechaNacimiento());
				pw.printf("%s;%s;%s;%s\n", c.getId(), c.getNombre(), c.getApellidos(), c.getFechaNacimiento());
			}
		} catch (IOException e) {
			pl("No se ha podido escribir el fichero");
		}
		
		System.out.println("Fichero exportado");
	}

	private static void importar() {
		pl("IMPORTAR");
		
		try (FileReader fr = new FileReader(FICHERO_CONTACTOS_CSV);
				Scanner sc = new Scanner(fr);) {
			
			oficina.despedirTodos();
			
			sc.nextLine();
			
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				System.out.println(linea);
				
				String[] partes = linea.split(";");
				
				Long id = "null".equals(partes[0]) ? null : Long.parseLong(partes[0]);
				String nombre = "null".equals(partes[1]) ? null : partes[1].trim();
				String apellidos = "null".equals(partes[2]) ? null : partes[2].trim();
				LocalDate fechaNacimiento = "null".equals(partes[3]) ? null : LocalDate.parse(partes[3]);
				
				Contacto c = new Contacto(id, nombre, apellidos, fechaNacimiento);
				
				oficina.contratar(c);
			}
		} catch (IOException e) {
			pl("No se ha podido leer el fichero");
		}
		
		System.out.println("Fichero importado");
	}

	private static void guardar() {
		pl("GUARDAR");
		
		try (FileOutputStream fos = new FileOutputStream(FICHERO_CONTACTOS);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(oficina);
		} catch (IOException e) {
			pl("No se ha podido escribir el fichero");
			e.printStackTrace();
		}
	}

	private static void cargar() {
		pl("CARGAR");
		
		try (FileInputStream fis = new FileInputStream(FICHERO_CONTACTOS);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			oficina = (Oficina) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			pl("No se ha podido leer el fichero");
		}
	}

	private static void salir() {
//		exportar();
		guardar();

		pl("SALIENDO");
	}

	private static void opcionNoEncontrada() {
		pl("Opción no encontrada");
	}
}
