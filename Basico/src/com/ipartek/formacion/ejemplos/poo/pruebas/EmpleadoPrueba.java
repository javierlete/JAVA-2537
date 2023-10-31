package com.ipartek.formacion.ejemplos.poo.pruebas;

import java.time.LocalDate;

import com.ipartek.formacion.ejemplos.poo.Contacto;
import com.ipartek.formacion.ejemplos.poo.Empleado;

public class EmpleadoPrueba {

	public static void main(String[] args) {
		Empleado e = new Empleado();

		System.out.println(e);

		e.setId(1L);
		e.setNombre("Juan");
		e.setApellidos("Perez");
		e.setFechaNacimiento(LocalDate.of(2000, 1, 1));
		e.setDni("12345678A");

		System.out.println(e);

		Empleado e2 = new Empleado(2L, "Pepe", "Pérez", LocalDate.now().minusYears(20), "12345678A");

		System.out.println(e2);

		Contacto c = e;

		System.out.print("CONTACTO: ");
		System.out.println(c);
		
//		System.out.println(c.getDni());

		Empleado e3 = (Empleado) c;

		System.out.println(e3);

		Contacto c2 = new Contacto();

		if (c2 instanceof Empleado e4) {
			// Empleado e4 = (Empleado) c2;
			System.out.println(e4);
		} else {
			System.out.println("No es un empleado");
		}
		
		Object o = new Object();
		
		System.out.println(o.toString());
		System.out.println(o.equals(new Object()));
		System.out.println(Integer.toHexString(o.hashCode()));
		System.out.println(o.getClass().getName());
		
		System.out.println(e);
	}

}
