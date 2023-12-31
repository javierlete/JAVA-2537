<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.time.LocalDate, java.util.TreeMap, com.ipartek.formacion.Contacto, java.sql.*"%>
<%@ include file="includes/cabecera.jsp" %>

<%
String editar = request.getParameter("editar");
String borrar = request.getParameter("borrar");

String nombre = request.getParameter("nombre");
String apellidos = request.getParameter("apellidos");
String fecha = request.getParameter("fecha");

if (borrar != null) {
	Long id = Long.parseLong(borrar);

	PreparedStatement pstDelete = con.prepareStatement("DELETE FROM contactos WHERE id = ?");
	pstDelete.setLong(1, id);

	pstDelete.executeUpdate();

	pstDelete.close();
}

if (nombre != null && apellidos != null && fecha != null) {
	LocalDate fechaNacimiento = null;

	if (fecha.trim().length() != 0) {
		fechaNacimiento = LocalDate.parse(fecha);
	}

	if (nombre.trim().length() == 0) {
		nombre = "Anónimo";
	}

	if (apellidos.trim().length() == 0) {
		apellidos = null;
	}

	Contacto guardar = new Contacto(nombre, apellidos, fechaNacimiento);

	Long id = null;

	String sId = request.getParameter("id");

	PreparedStatement pstGuardar = null;

	if (sId != null && sId.trim().length() > 0) {
		id = Long.parseLong(sId);
		guardar.setId(id);

		pstGuardar = con.prepareStatement("UPDATE contactos SET nombre=?, apellidos=?, fecha_nacimiento=? WHERE id=?");
		pstGuardar.setLong(4, id);
	} else {
		pstGuardar = con.prepareStatement("INSERT INTO contactos (nombre, apellidos, fecha_nacimiento) VALUES (?,?,?)");
	}

	pstGuardar.setString(1, guardar.getNombre());
	pstGuardar.setString(2, guardar.getApellidos());
	pstGuardar.setString(3, guardar.getFechaNacimiento() == null ? null : guardar.getFechaNacimiento().toString());

	pstGuardar.executeUpdate();

	pstGuardar.close();
}

TreeMap<Long, Contacto> contactos = obtenerContactos();

Contacto contacto = new Contacto();

if (editar != null) {
	Long id = Long.parseLong(editar);
	contacto = contactos.get(id);
}

%>
	<div class="table-responsive">
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
					<td><%=c.getApellidos() == null ? "" : c.getApellidos()%></td>
					<td><%=c.getFechaNacimiento() == null ? "" : c.getFechaNacimiento()%></td>
					<td><a class="btn btn-sm btn-primary"
						href="?editar=<%=c.getId()%>">Editar</a> <a
						class="btn btn-sm btn-danger" href="?borrar=<%=c.getId()%>">Borrar</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
	
	<form class="container needs-validation" action="listado.jsp"
		method="post" novalidate>
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="id">Id</label>
			<div class="col-sm">
				<input readonly class="form-control" type="text" name="id" id="id"
					value="<%=contacto.getId() != null ? contacto.getId() : ""%>">
			</div>
		</div>
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="nombre">Nombre</label>
			<div class="col-sm">
				<input pattern="^[\p{L}' ]{1,50}$" maxlength="50" required
					class="form-control" type="text" id="nombre" name="nombre"
					placeholder="Nombre"
					value="<%=contacto.getNombre().equals("Anónimo") ? "" : contacto.getNombre()%>">
				<div class="invalid-feedback">El nombre es obligatorio y no
					debe tener caracteres extraños</div>
			</div>
		</div>
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="apellidos">Apellidos</label>
			<div class="col-sm">
				<input pattern="^[\p{L}' ]{0,100}$" maxlength="100"
					class="form-control" type="text" id="apellidos" name="apellidos"
					placeholder="Apellidos"
					value="<%=contacto.getApellidos() != null ? contacto.getApellidos() : ""%>">
				<div class="invalid-feedback">Los apellidos no deben tener
					caracteres extraños</div>
			</div>
		</div>
		<div class="row mb-2">
			<label class="col-sm-4 col-form-label" for="fecha">Fecha de
				nacimiento</label>
			<div class="col-sm">
				<input class="form-control" type="date" id="fecha" name="fecha"
					placeholder="Fecha de nacimiento" max="<%=LocalDate.now()%>"
					value="<%=contacto.getFechaNacimiento()%>">
				<div class="invalid-feedback">La fecha de nacimiento debe ser
					anterior a la fecha actual</div>
			</div>
		</div>

		<div class="row">
			<div class="offset-sm-4 col-sm">
				<button class="btn btn-primary">Guardar</button>
			</div>
		</div>
	</form>

	<script src="js/validacion-bootstrap.js"></script>

<%@ include file="includes/pie.jsp" %>
