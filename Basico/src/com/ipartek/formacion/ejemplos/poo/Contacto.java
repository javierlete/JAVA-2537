package com.ipartek.formacion.ejemplos.poo;

import java.time.LocalDate;
import java.util.Objects;

public class Contacto {
	// Variables de instancia
	private Long id;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	
	// Constructores
	// Source/Generate Constructor using Fields...
	public Contacto(Long id, String nombre, String apellidos, LocalDate fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setFechaNacimiento(fechaNacimiento);
	}
	
	public Contacto(String nombre, String apellidos, LocalDate fechaNacimiento) {
		this(null, nombre, apellidos, fechaNacimiento);
	}
	
	public Contacto(String nombre, LocalDate fechaNacimiento) {
		this(null, nombre, null, fechaNacimiento);
	}

	public Contacto(String nombre) {
		this(null, nombre, null, null);
	}
	
	public Contacto() {
		this(null, "Anónimo", null, null);
	}
	
	// Constructor de copia
	public Contacto(Contacto contacto) {
		this(contacto.getId(), contacto.getNombre(), contacto.getApellidos(), contacto.getFechaNacimiento());
	}
	
	// Setters y getters
	// Source/Generate Getters and Setters...
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		if(id != null && id <= 0) {
			throw new RuntimeException("El id debe ser positivo");
		}
		
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre == null || nombre.trim().length() == 0) {
			throw new RuntimeException("El nombre es obligatorio debe tener al menos un caracter");
		}
		
		this.nombre = nombre.trim();
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		if(apellidos != null && apellidos.trim().length() == 0) {
			throw new RuntimeException("Los apellidos deben tener al menos un caracter");
		}
		
		this.apellidos = apellidos == null ? null : apellidos.trim();
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if(fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
			throw new RuntimeException("La fecha de nacimiento debe ser anterior a la actual");
		}
		
		this.fechaNacimiento = fechaNacimiento;
	}

	// Source/Generate hashcode() and equals()
	
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, fechaNacimiento, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	// Source/Generate toString()...
	@Override
	public String toString() {
		return "Contacto [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + "]";
	}
	
	public String getNombreCompleto() {
		return nombre + " " + apellidos;
	}
}
