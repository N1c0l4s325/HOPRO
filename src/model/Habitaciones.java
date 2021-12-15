/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Habitaciones {
	private int ID;
	private int numHab;
	private int capacidad;
	private String nivel;
	private String Descripcion;
	private String tipoHabitacion;
	private Double precio;
	private String estado;
	
	
	public Habitaciones() {
		
	}


	public Habitaciones(int iD, int numHab, int capacidad, String nivel, String descripcion, String tipoHabitacion,
			Double precio, String estado) {
		super();
		ID = iD;
		this.numHab = numHab;
		this.capacidad = capacidad;
		this.nivel = nivel;
		Descripcion = descripcion;
		this.tipoHabitacion = tipoHabitacion;
		this.precio = precio;
		this.estado = estado;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getNumHab() {
		return numHab;
	}


	public void setNumHab(int numHab) {
		this.numHab = numHab;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public String getNivel() {
		return nivel;
	}


	public void setNivel(String nivel) {
		this.nivel = nivel;
	}


	public String getDescripcion() {
		return Descripcion;
	}


	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


	public String getTipoHabitacion() {
		return tipoHabitacion;
	}


	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	
	
	
	
}
