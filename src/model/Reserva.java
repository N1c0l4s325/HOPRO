/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Reserva {
    private int id;
    private int id_empleado;
	private int id_habitacion;
    private int	id_persona;
    private Date fechaReserva;
    private Date fechaIngreso;
    private Date fechaSalida;
    private int cantidadNoches;
    private double importe;
    private String estado;
    private Habitaciones habitacion;
    private Persons persona;
 
    
    
    
	public Reserva() {
		
	}




	public Reserva(int id, int id_empleado, int id_habitacion, int id_persona, Date fechaReserva, Date fechaIngreso,
			Date fechaSalida, String estado, Habitaciones habitacion, Persons persona, int cantidadNoches, double importe) {
		super();
		this.id = id;
		this.id_empleado = id_empleado;
		this.id_habitacion = id_habitacion;
		this.id_persona = id_persona;
		this.fechaReserva = fechaReserva;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.estado = estado;
		this.habitacion = habitacion;
		this.persona = persona;
		this.cantidadNoches = cantidadNoches;
		this.importe = importe;
	}




	public int getId() {
		return id;
	}




	public double getImporte() {
		return importe;
	}




	public void setImporte(double importe) {
		this.importe = importe;
	}




	public int getCantidadNoches() {
		return cantidadNoches;
	}




	public void setCantidadNoches(int cantidadNoches) {
		this.cantidadNoches = cantidadNoches;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getId_empleado() {
		return id_empleado;
	}




	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}




	public int getId_habitacion() {
		return id_habitacion;
	}




	public void setId_habitacion(int id_habitacion) {
		this.id_habitacion = id_habitacion;
	}




	public int getId_persona() {
		return id_persona;
	}




	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}




	public Date getFechaReserva() {
		return fechaReserva;
	}




	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}




	public Date getFechaIngreso() {
		return fechaIngreso;
	}




	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}




	public Date getFechaSalida() {
		return fechaSalida;
	}




	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}




	public String getEstado() {
		return estado;
	}




	public void setEstado(String estado) {
		this.estado = estado;
	}




	public Habitaciones getHabitacion() {
		return habitacion;
	}




	public void setHabitacion(Habitaciones habitacion) {
		this.habitacion = habitacion;
	}




	public Persons getPersona() {
		return persona;
	}




	public void setPersona(Persons persona) {
		this.persona = persona;
	}




	



	
    
    	

    
    
    

    
}
