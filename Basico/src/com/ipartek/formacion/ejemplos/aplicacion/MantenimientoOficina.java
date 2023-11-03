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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.ipartek.formacion.ejemplos.poo.Contacto;

public class MantenimientoOficina {
	private static final String FORMATO = "%2s %-20s %-20s %-10s\n";

	private static final String url = "jdbc:sqlite:sql/contactos.db";

	private static final String SELECT = "SELECT * FROM contactos";
	private static final String SELECT_ID = "SELECT * FROM contactos WHERE id = ?";
	private static final String INSERT = "INSERT INTO contactos (nombre, apellidos, fecha_nacimiento) VALUES (?,?,?)";
	private static final String INSERT_ID = "INSERT INTO contactos (id, nombre, apellidos, fecha_nacimiento) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE contactos SET nombre = ?, apellidos = ?, fecha_nacimiento = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM contactos WHERE id = ?";
	private static final String TRUNCATE = "DELETE FROM contactos; DELETE FROM SQLITE_SEQUENCE WHERE name='contactos';";

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

//	private static Oficina oficina = new Oficina(null, "Bilbao", new Contacto("Javier"));

//	static {
//		OFICINA.contratar(new Contacto("Uno"));
//		OFICINA.contratar(new Contacto("Dos"));
//		OFICINA.contratar(new Contacto("Tres"));
//		OFICINA.contratar(new Contacto("Cuatro"));
//	}

	private static Connection con = null;

	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			pl("No se ha podido conectar a la base de datos");
		}

		int opcion;

//		cargar();
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

		try (PreparedStatement pst = con.prepareStatement(SELECT); ResultSet rs = pst.executeQuery()) {
			System.out.printf(FORMATO, "ID", "NOMBRE", "APELLIDOS", "FECHA");

			while (rs.next()) {
				System.out.printf(FORMATO, rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("fecha_nacimiento"));
			}
		} catch (SQLException e) {
			pl("No se ha podido listar los contactos");
		}

	}

	private static void buscar() {
		pl("BUSCAR POR ID");

		Long id = rLong("ID");

		try (PreparedStatement pst = con.prepareStatement(SELECT_ID)) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					System.out.printf(FORMATO, "ID", "NOMBRE", "APELLIDOS", "FECHA");
					System.out.printf(FORMATO, rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
							rs.getString("fecha_nacimiento"));
				} else {
					System.out.println("No encontrado");
				}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void insertar() {
		pl("INSERTAR");

		Contacto contacto = new Contacto();
		pedirDatosContacto(contacto);

		try (PreparedStatement pst = con.prepareStatement(INSERT)) {
			pst.setString(1, contacto.getNombre());
			pst.setString(2, contacto.getApellidos());
			pst.setString(3, contacto.getFechaNacimiento().toString()); // java.sql.Date.valueOf(LocalDate.now()));

			pst.executeUpdate();
		} catch (SQLException e) {
			pl("No se ha podido insertar el contacto");
		}

		listado();
	}

	private static void modificar() {
		pl("MODIFICAR");

		Long id = rLong("ID");

		Contacto contacto = new Contacto();
		pedirDatosContacto(contacto);

		try (PreparedStatement pst = con.prepareStatement(UPDATE)) {
			pst.setString(1, contacto.getNombre());
			pst.setString(2, contacto.getApellidos());
			pst.setString(3, contacto.getFechaNacimiento().toString()); // java.sql.Date.valueOf(LocalDate.now()));

			pst.setLong(4, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			pl("No se ha podido modificar el contacto");
		}

		listado();
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

		try (PreparedStatement pst = con.prepareStatement(DELETE)) {
			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			pl("No se ha podido borrar el contacto");
		}

		listado();
	}

	private static void exportar() {
		pl("EXPORTAR");

		try (FileWriter fw = new FileWriter(FICHERO_CONTACTOS_CSV); PrintWriter pw = new PrintWriter(fw)) {
			System.out.println("\"ID\";\"Nombre\";\"Apellidos\";\"FechaNacimiento\"");
			pw.print("""
					"ID";"Nombre";"Apellidos";"FechaNacimiento"
					""");

			try (PreparedStatement pst = con.prepareStatement(SELECT); ResultSet rs = pst.executeQuery()) {
				System.out.printf(FORMATO, "ID", "NOMBRE", "APELLIDOS", "FECHA");

				while (rs.next()) {
					System.out.printf("%s;%s;%s;%s\n", rs.getInt("id"), rs.getString("nombre"),
							rs.getString("apellidos"), rs.getString("fecha_nacimiento"));
					pw.printf("%s;%s;%s;%s\n", rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
							rs.getString("fecha_nacimiento"));
				}
			} catch (SQLException e) {
				pl("No se ha podido listar los contactos");
			}
		} catch (IOException e) {
			pl("No se ha podido escribir el fichero");
		}

		System.out.println("Fichero exportado");
	}

	private static void importar() {
		pl("IMPORTAR");

		try (FileReader fr = new FileReader(FICHERO_CONTACTOS_CSV);
				Scanner sc = new Scanner(fr);
				PreparedStatement pst = con.prepareStatement(TRUNCATE);) {

			pst.executeUpdate();

			sc.nextLine();

			PreparedStatement pstInsert = con.prepareStatement(INSERT_ID);

			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				System.out.println(linea);

				String[] partes = linea.split(";");

				Long id = "null".equals(partes[0]) ? null : Long.parseLong(partes[0]);
				String nombre = "null".equals(partes[1]) ? null : partes[1].trim();
				String apellidos = "null".equals(partes[2]) ? null : partes[2].trim();
				LocalDate fechaNacimiento = "null".equals(partes[3]) ? null : LocalDate.parse(partes[3]);

				pstInsert.setLong(1, id);
				pstInsert.setString(2, nombre);
				pstInsert.setString(3, apellidos);
				pstInsert.setString(4, fechaNacimiento.toString()); // java.sql.Date.valueOf(LocalDate.now()));

				pstInsert.executeUpdate();
			}
		} catch (IOException e) {
			pl("No se ha podido leer el fichero");
		} catch (NumberFormatException e) {
			pl("Error de conversión numérica");
		} catch (SQLException e) {
			pl("No se ha podido actualizar la base de datos");
			e.printStackTrace();
		}

		System.out.println("Fichero importado");
	}

	private static void guardar() {
		pl("GUARDAR");

		try (FileOutputStream fos = new FileOutputStream(FICHERO_CONTACTOS);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			var listado = new ArrayList<Contacto>();

			try (PreparedStatement pst = con.prepareStatement(SELECT); ResultSet rs = pst.executeQuery()) {
				System.out.printf(FORMATO, "ID", "NOMBRE", "APELLIDOS", "FECHA");

				while (rs.next()) {
					listado.add(new Contacto(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
							LocalDate.parse(rs.getString("fecha_nacimiento"))));
				}
			} catch (SQLException e) {
				pl("No se ha podido listar los contactos");
			}

			oos.writeObject(listado);
		} catch (IOException e) {
			pl("No se ha podido escribir el fichero");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static void cargar() {
		pl("CARGAR");

		try (FileInputStream fis = new FileInputStream(FICHERO_CONTACTOS);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			var listado = (ArrayList<Contacto>) ois.readObject();

			try (PreparedStatement pst = con.prepareStatement(TRUNCATE)) {
				pst.executeUpdate();

				PreparedStatement pstInsert = con.prepareStatement(INSERT_ID);

				for (Contacto c : listado) {
					pstInsert.setLong(1, c.getId());
					pstInsert.setString(2, c.getNombre());
					pstInsert.setString(3, c.getApellidos());
					pstInsert.setString(4, c.getFechaNacimiento().toString()); // java.sql.Date.valueOf(LocalDate.now()));

					pstInsert.executeUpdate();
				}
			} catch (SQLException e) {
				pl("No se ha podido actualizar la base de datos");
			}
		} catch (IOException | ClassNotFoundException e) {
			pl("No se ha podido leer el fichero");
		}
	}

	private static void salir() {
//		exportar();
//		guardar();

		pl("SALIENDO");
	}

	private static void opcionNoEncontrada() {
		pl("Opción no encontrada");
	}
}
