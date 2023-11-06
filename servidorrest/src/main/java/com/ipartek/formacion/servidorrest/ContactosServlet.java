package com.ipartek.formacion.servidorrest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.TreeMap;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contactos/*")
public class ContactosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final TreeMap<Long, Contacto> contactos = new TreeMap<>();

	static {
		contactos.put(1L, new Contacto(1L, "Uno", "Unez", LocalDate.now()));
		contactos.put(2L, new Contacto(2L, "Dos", "Dosez", LocalDate.now()));
	}

	private static final Jsonb JSONB = JsonbBuilder.create();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getPathInfo().substring(1);

		if (id.length() == 0) {
			JSONB.toJson(contactos.values(), response.getWriter());
		} else {
			Long elId = Long.parseLong(id);
			
			Contacto c = contactos.get(elId);
			
			JSONB.toJson(c, response.getWriter());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Contacto c = JSONB.fromJson(request.getReader(), Contacto.class);
		
		contactos.put(c.getId(), c);
		
		JSONB.toJson(c, response.getWriter());
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getPathInfo().substring(1);
		
		Long id = Long.parseLong(sId);
		
		contactos.remove(id);
	}
}
