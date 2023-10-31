package com.ipartek.formacion.ejemplos.poo;

import java.time.LocalDate;
import java.util.Objects;

public class Empleado extends Contacto {
	private static final long serialVersionUID = -112416451138341635L;
	
	public static final String NOMBRE_POR_DEFECTO = "Juan";
	public static final String APELLIDOS_POR_DEFECTO = "Nadie";
	
	private String dni;
	
	public Empleado(Long id, String nombre, String apellidos, LocalDate fechaNacimiento, String dni) {
		super(id, nombre, apellidos, fechaNacimiento);
		setDni(dni);
	}

	public Empleado(Long id, String nombre, String apellidos, LocalDate fechaNacimiento) {
		this(id, nombre, apellidos, fechaNacimiento, null);
	}

	public Empleado(String nombre, String apellidos, LocalDate fechaNacimiento) {
		this(null, nombre, apellidos, fechaNacimiento, null);
	}

	public Empleado(String nombre, LocalDate fechaNacimiento) {
		this(null, nombre, APELLIDOS_POR_DEFECTO, fechaNacimiento, null);
	}

	public Empleado(String nombre) {
		this(null, nombre, APELLIDOS_POR_DEFECTO, null, null);
	}

	public Empleado() {
		this(null, NOMBRE_POR_DEFECTO, APELLIDOS_POR_DEFECTO, null, null);
	}

	public Empleado(Contacto contacto) {
		this(contacto.getId(), contacto.getNombre(), contacto.getApellidos(), 
				contacto.getFechaNacimiento(), null);
	}

	@Override
	public void setApellidos(String apellidos) {
		if(apellidos == null) {
			throw new RuntimeException("Los apellidos son obligatorios para los empleados");
		}
		
		super.setApellidos(apellidos);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dni);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "Empleado [id=" + getId() + ", nombre=" + getNombre() + 
				", apellidos=" + getApellidos() + ", fechaNacimiento=" + getFechaNacimiento() + 
				", dni=" + dni + "]"; 
	}
}
