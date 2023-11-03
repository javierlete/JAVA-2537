package com.ipartek.formacion.servidorrest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.TreeMap;

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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String id = request.getPathInfo().substring(1);

		if (id.length() == 0) {

			out.println("[");

			int i = 0;

			for (Contacto c : contactos.values()) {
				i++;
				out.printf("{\"id\":%s, \"nombre\":\"%s\", \"apellidos\":\"%s\", \"fecha_nacimiento\":\"%s\"}",
						c.getId(), c.getNombre(), c.getApellidos(), c.getFechaNacimiento());

				if (contactos.size() > i) {
					out.println(",");
				}
			}

			out.println("]");
		} else {
			Long elId = Long.parseLong(id);
			
			Contacto c = contactos.get(elId);
			
			out.printf("{\"id\":%s, \"nombre\":\"%s\", \"apellidos\":\"%s\", \"fecha_nacimiento\":\"%s\"}",
					c.getId(), c.getNombre(), c.getApellidos(), c.getFechaNacimiento());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		BufferedReader br = request.getReader();
		
		StringBuffer json = new StringBuffer();
		
		String linea;
		while((linea = br.readLine()) != null) {
			json.append(linea);
		}
		
		String[] pedazos = json.toString().split("[:,]");
		
		String pId = pedazos[1].trim();
		String pNombre = pedazos[3].replace("\"", "");
		String pApellidos = pedazos[5].replace("\"", "");
		String pFecha = pedazos[7].trim().replace("\"", "").replace("}", "");
		
		Contacto c = new Contacto(Long.parseLong(pId), pNombre, pApellidos, LocalDate.parse(pFecha));
		
		contactos.put(c.getId(), c);
		
		out.println(json);
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
