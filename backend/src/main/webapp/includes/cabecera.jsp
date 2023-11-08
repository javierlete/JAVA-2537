<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.sql.*, java.time.*, java.util.*, com.ipartek.formacion.Contacto"%>
<%!private final static String URL = "jdbc:sqlite:/sqlite/contactos.db";
	private static Connection con = null;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	TreeMap<Long, Contacto> obtenerContactos() {
		TreeMap<Long, Contacto> contactos = new TreeMap<>();
		
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM contactos");
			ResultSet rs = pst.executeQuery();


			while (rs.next()) {
				Long id = rs.getLong("id");
				LocalDate fechaNacimiento = null;

				if (rs.getString("fecha_nacimiento") != null) {
					fechaNacimiento = LocalDate.parse(rs.getString("fecha_nacimiento"));
				}

				Contacto contacto = new Contacto(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						fechaNacimiento);

				contactos.put(id, contacto);
			}

			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactos;
	}%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contactos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="mb-3 navbar navbar-expand-lg bg-body-tertiary"
		data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp">Contactos</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Principal</a></li>
				</ul>
				<ul class="navbar-nav mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="listado.jsp">Administración</a></li>
				</ul>
			</div>
		</div>
	</nav>