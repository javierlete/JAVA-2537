package com.ipartek.formacion.ejemplos.poo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Oficina implements Serializable {
	private static final long serialVersionUID = -825254392410083762L;

	private Long id;
	private String nombre;
	private Contacto director;

	private ArrayList<Contacto> empleados = new ArrayList<>();

	public Oficina(Long id, String nombre, Contacto director) {
		setId(id);
		setNombre(nombre);
		setDirector(director);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Contacto getDirector() {
		return director;
	}

	public void setDirector(Contacto director) {
		this.director = director;
	}

	public ArrayList<Contacto> getEmpleados() {
		return empleados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(director, empleados, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oficina other = (Oficina) obj;
		return Objects.equals(director, other.director) && Objects.equals(empleados, other.empleados)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Oficina [id=" + id + ", nombre=" + nombre + ", director=" + director + ", empleados=" + empleados + "]";
	}

	public Contacto buscarEmpleadoPorId(long id) {
		for (Contacto e : empleados) {
			if (e.getId() == id) {
				return e;
			}
		}

		return null;
	}

	public Contacto contratar(Contacto empleado) {
		if (empleado.getId() == null) {

			Long id = 0L;

			for (Contacto e : empleados) {
				if (e.getId() > id) {
					id = e.getId();
				}
			}

			id++;

			empleado.setId(id);
		}

		empleados.add(empleado);

		return empleado;
	}

	public void despedir(Long id) {
		empleados.remove(buscarEmpleadoPorId(id));
	}

	public void despedirTodos() {
		empleados.clear();
	}
}
