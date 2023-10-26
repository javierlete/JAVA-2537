package com.ipartek.formacion.ejemplos.poo.pruebas;

import com.ipartek.formacion.ejemplos.poo.Contacto;
import com.ipartek.formacion.ejemplos.poo.Oficina;

public class OficinaPrueba {
	public static void main(String[] args) {
		Contacto director = new Contacto("Javier");
		Oficina oficina = new Oficina(1L, "Bilbao", director);
		
		Contacto empleado1 = new Contacto("Pepe");
		
		oficina.contratar(empleado1);
		
		Contacto empleado2 = oficina.contratar(new Contacto("Juan"));

		System.out.println(empleado2);
		System.out.println(oficina.contratar(new Contacto("Pedro")));
		
		System.out.println("EMPLEADOS");
		
		for(Contacto e: oficina.getEmpleados()) {
			System.out.println(e);
		}
	}
}
