<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.time.LocalDate, java.util.TreeMap, com.ipartek.formacion.Contacto, java.sql.*"%>
<%!private final static TreeMap<Long, Contacto> contactos = new TreeMap<>();

	private final static String URL = "jdbc:sqlite:/sqlite/contactos.db";

	private static Long contador = 3L;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}%>
<%
Connection con = DriverManager.getConnection(URL);
PreparedStatement pst = con.prepareStatement("SELECT * FROM contactos");
ResultSet rs = pst.executeQuery();

while (rs.next()) {
	Long id = rs.getLong("id");
	Contacto contacto = new Contacto(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
	LocalDate.parse(rs.getString("fecha_nacimiento")));

	contactos.put(id, contacto);
}

String editar = request.getParameter("editar");
String borrar = request.getParameter("borrar");

String nombre = request.getParameter("nombre");
String apellidos = request.getParameter("apellidos");
String fecha = request.getParameter("fecha");

if (nombre != null && apellidos != null && fecha != null) {
	Contacto guardar = new Contacto(contador, nombre, apellidos, LocalDate.parse(fecha));

	Long id = null;

	String sId = request.getParameter("id");

	if (sId != null && sId.trim().length() > 0) {
		id = Long.parseLong(sId);
	} else {
		id = contador++;
	}

	guardar.setId(id);
	contactos.put(id, guardar);
}

Contacto contacto = new Contacto();

if (editar != null) {
	Long id = Long.parseLong(editar);
	contacto = contactos.get(id);
}

if (borrar != null) {
	Long id = Long.parseLong(borrar);
	contactos.remove(id);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Hola Mundo</title>
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
	<table class="table table-hover table-striped table-bordered">
		<caption>Contactos</caption>
		<thead class="table-dark">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Fecha de nacimiento</th>
				<th>OPCIONES</th>
			</tr>
		</thead>

		<tbody>
			<%
			for (Contacto c : contactos.values()) {
			%>
			<tr>
				<td><%=c.getId()%></td>
				<td><%=c.getNombre()%></td>
				<td><%=c.getApellidos()%></td>
				<td><%=c.getFechaNacimiento()%></td>
				<td><a class="btn btn-sm btn-primary"
					href="?editar=<%=c.getId()%>">Editar</a> <a
					class="btn btn-sm btn-danger" href="?borrar=<%=c.getId()%>">Borrar</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<form class="container" action="listado.jsp" method="post">
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="id">Id</label>
			<div class="col-sm">
				<input class="form-control" type="text" name="id" id="id"
					value="<%=contacto.getId() != null ? contacto.getId() : ""%>">
			</div>
		</div>
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="nombre">Nombre</label>
			<div class="col-sm">
				<input class="form-control" type="text" id="nombre" name="nombre"
					placeholder="Nombre"
					value="<%=contacto.getNombre().equals("Anónimo") ? "" : contacto.getNombre()%>">
			</div>
		</div>
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="apellidos">Apellidos</label>
			<div class="col-sm">
				<input class="form-control" type="text" id="apellidos"
					name="apellidos" placeholder="Apellidos"
					value="<%=contacto.getApellidos() != null ? contacto.getApellidos() : ""%>">
			</div>
		</div>
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="fecha">Fecha de
				nacimiento</label>
			<div class="col-sm">
				<input class="form-control" type="date" id="fecha" name="fecha"
					placeholder="Fecha de nacimiento"
					value="<%=contacto.getFechaNacimiento()%>">
			</div>
		</div>

		<div class="row">
			<div class="offset-sm-4 col-sm">
				<button class="btn btn-primary">Guardar</button>
			</div>
		</div>
	</form>

</body>
</html>
