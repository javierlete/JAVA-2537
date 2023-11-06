<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.time.LocalDate, java.util.TreeMap, com.ipartek.formacion.Contacto" %>
<%!
private final static TreeMap<Long, Contacto> contactos = new TreeMap<>();

private static Long contador = 3L;

static {
	contactos.put(1L, new Contacto(1L, "Uno", "Unez", LocalDate.now()));
	contactos.put(2L, new Contacto(2L, "Dos", "Dosez", LocalDate.now()));
}
%>
<%
String nombre = request.getParameter("nombre");
String apellidos = request.getParameter("apellidos");
String fecha = request.getParameter("fecha");

if(nombre != null && apellidos != null && fecha != null) {
	contactos.put(contador++, new Contacto(contador, nombre, apellidos, LocalDate.parse(fecha)));
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
			</tr>
		</thead>

		<tbody>
			<% for (Contacto c : contactos.values()) { %>
				<tr>
					<td><%= c.getId() %></td>
					<td><%= c.getNombre() %></td>
					<td><%= c.getApellidos() %></td>
					<td><%= c.getFechaNacimiento() %></td>
				</tr>
			<% } %>
		</tbody>
	</table>

	<form method="post">
		<input type="text" name="nombre" placeholder="Nombre">
		<input type="text" name="apellidos" placeholder="Apellidos">
		<input type="date" name="fecha" placeholder="Fecha de nacimiento">
		
		<button>Guardar</button>
	</form>

</body>
</html>
