<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/cabecera.jsp"%>

<%
var contactos = obtenerContactos();
%>

<div class="container">

	<div
		class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">
		<%
		for (Contacto c : contactos.values()) {
		%>
		<div class="col">
			<div class="card h-100">
				<img src="https://picsum.photos/300/200?<%=c.getId()%>" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title"><%=c.getNombre()%>
						<%=c.getApellidos() != null ? c.getApellidos() : ""%></h5>
				</div>
				<div class="card-footer">
					<small class="text-body-secondary"><%=c.getFechaNacimiento() != null ? c.getFechaNacimiento() : ""%></small>
				</div>
			</div>
		</div>
		<%
		}
		%>
	</div>

</div>

<%@ include file="includes/pie.jsp"%>
