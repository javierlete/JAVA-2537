package com.ipartek.formacion.ejemplos.poo.pruebas;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ipartek.formacion.ejemplos.poo.Contacto;

public class ContactoPrueba {

	public static void main(String[] args) {
		Contacto contacto; // vale null por defecto

		contacto = new Contacto();

		contacto.setId(1L);
		contacto.setNombre("          asdfsdf    ");
		contacto.setApellidos("   asjdhfjhgjfkdg   ");
		contacto.setFechaNacimiento(LocalDate.of(2023, 10, 25));

		System.out.println(String.format("%s: %s %s; %s", contacto.getId(), contacto.getNombre(),
				contacto.getApellidos(), contacto.getFechaNacimiento()));

		Contacto contacto2 = new Contacto(2L, "     Pepe", null, LocalDate.of(2000, 1, 2));

		System.out.println(String.format("%s: %s %s; %s", contacto2.getId(), contacto2.getNombre(),
				contacto2.getApellidos(), contacto2.getFechaNacimiento()));

		Contacto contacto3 = new Contacto("Sin ID", "Otro apellido", LocalDate.now());

		System.out.println(String.format("%s: %s %s; %s", contacto3.getId(), contacto3.getNombre(),
				contacto3.getApellidos(), contacto3.getFechaNacimiento()));

		Contacto contacto4 = new Contacto("Sin ID ni apellidos", LocalDate.now());

		System.out.println(String.format("%s: %s %s; %s", contacto4.getId(), contacto4.getNombre(),
				contacto4.getApellidos(), contacto4.getFechaNacimiento()));

		Contacto contacto5 = new Contacto("Sólo nombre");

		System.out.println(String.format("%s: %s %s; %s", contacto5.getId(), contacto5.getNombre(),
				contacto5.getApellidos(), contacto5.getFechaNacimiento()));

		Contacto anonimo = new Contacto();

		System.out.println(String.format("%s: %s %s; %s", anonimo.getId(), anonimo.getNombre(), anonimo.getApellidos(),
				anonimo.getFechaNacimiento()));

		// Uso de constructor de copia
		Contacto nuevo = new Contacto(contacto);

		System.out.println(nuevo == contacto); // NO son el mismo (==)(false)
		System.out.println(nuevo.equals(contacto)); // Pero SI tienen los mismos datos (equals)(true)

		nuevo.setNombre("Nuevo nombre");

		System.out.println(nuevo.toString());
		System.out.println(contacto);

		System.out.println(contacto.getNombreCompleto());

		var contactos = new ArrayList<Contacto>();
		
		contactos.add(contacto);
		contactos.add(contacto2);
		contactos.add(contacto3);
		contactos.add(contacto4);
		contactos.add(contacto5);
		
		for(Contacto c: contactos) {
			System.out.println(c.getNombreCompleto());
			System.out.println(c);
		}
		
		for(int i = 0; i < contactos.size();  i++) {
			Contacto c = contactos.get(i);
			
			System.out.println(c.getNombreCompleto());
			System.out.println(c);
		}
	}

}
