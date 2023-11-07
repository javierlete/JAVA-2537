<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.time.LocalDate, java.util.TreeMap, com.ipartek.formacion.Contacto"%>
<%!private final static TreeMap<Long, Contacto> contactos = new TreeMap<>();

	private static Long contador = 3L;

	static {
		contactos.put(1L, new Contacto(1L, "Uno", "Unez", LocalDate.now()));
		contactos.put(2L, new Contacto(2L, "Dos", "Dosez", LocalDate.now()));
	}%>
<%
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
</head>
<body>
	<table>
		<caption>Contactos</caption>
		<thead>
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
				<td><a href="?editar=<%=c.getId()%>">Editar</a> <a
					href="?borrar=<%=c.getId()%>">Borrar</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<form action="listado.jsp" method="post">
		<input type="text" name="id"
			value="<%=contacto.getId() != null ? contacto.getId() : ""%>">
		<input type="text" name="nombre" placeholder="Nombre"
			value="<%=contacto.getNombre().equals("Anónimo") ? "" : contacto.getNombre()%>">
		<input type="text" name="apellidos" placeholder="Apellidos"
			value="<%=contacto.getApellidos() != null ? contacto.getApellidos() : ""%>">
		<input type="date" name="fecha" placeholder="Fecha de nacimiento"
			value="<%=contacto.getFechaNacimiento()%>">

		<button>Guardar</button>
	</form>

</body>
</html>
