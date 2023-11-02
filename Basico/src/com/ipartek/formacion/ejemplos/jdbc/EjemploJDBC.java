package com.ipartek.formacion.ejemplos.jdbc;

import java.sql.*;
import java.time.LocalDate;

public class EjemploJDBC {

	private static final String FORMATO = "%2s %-20s %-20s %-10s\n";

	private static final String url = "jdbc:sqlite:sql/contactos.db";

	private static final String SELECT = "SELECT * FROM contactos";
	private static final String SELECT_ID = "SELECT * FROM contactos WHERE id = ?";
	private static final String INSERT = "INSERT INTO contactos (nombre, apellidos, fecha_nacimiento) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE contactos SET nombre = ?, apellidos = ?, fecha_nacimiento = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM contactos WHERE id = ?";
	

	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(url);
		PreparedStatement pst;
		ResultSet rs;
		
		String id = "1";
		
		pst = con.prepareStatement(SELECT_ID);
		pst.setLong(1, Long.parseLong(id));
		
		rs = pst.executeQuery();
		
		if(rs.next()) {
			System.out.printf(FORMATO, "ID", "NOMBRE", "APELLIDOS", "FECHA");
			System.out.printf(FORMATO, rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("fecha_nacimiento"));
		} else {
			System.out.println("No encontrado");
		}

		rs.close();
		pst.close();
		
		pst = con.prepareStatement(INSERT);
		
		pst.setString(1, "Juan");
		pst.setString(2, "Perez");
		pst.setString(3, LocalDate.now().toString()); // java.sql.Date.valueOf(LocalDate.now()));
		
		int numeroRegistrosModificados = pst.executeUpdate();
		
		pst.close();
		
		System.out.println(numeroRegistrosModificados + " registros insertados");
		
		pst = con.prepareStatement(UPDATE);
		pst.setString(1, "Modificado");
		pst.setString(2, "Modificadez");
		pst.setString(3, LocalDate.now().toString()); // java.sql.Date.valueOf(LocalDate.now()));
		pst.setLong(4, 3);
		
		numeroRegistrosModificados = pst.executeUpdate();
		
		pst.close();
		
		System.out.println(numeroRegistrosModificados + " registros actualizados");
		
		pst = con.prepareStatement(DELETE);
		pst.setInt(1, 2);
		
		numeroRegistrosModificados = pst.executeUpdate();
		
		pst.close();
		
		System.out.println(numeroRegistrosModificados + " registros eliminados");
		
		pst = con.prepareStatement(SELECT);
		rs = pst.executeQuery();
		
		System.out.printf(FORMATO, "ID", "NOMBRE", "APELLIDOS", "FECHA");
		
		while (rs.next()) {
			System.out.printf(FORMATO, rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("fecha_nacimiento"));
		}
		
		rs.close();
		pst.close();
		con.close();
	}

}
