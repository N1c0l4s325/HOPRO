/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Reclamos {

	private int ID;
	private int ID_empleado;
	private int ID_reserva;
	private String descripcion;
	private String fechaIngreso;
    private String estado;
	private Reserva reserva;
	private Habitaciones habitacion;
	
	
	public Reclamos() {
		
	}


	public Reclamos(int iD, int iD_empleado, int iD_reserva, String descripcion, String fechaIngreso, String estado,
			Reserva reserva, Habitaciones habitacion) {
		super();
		ID = iD;
		ID_empleado = iD_empleado;
		ID_reserva = iD_reserva;
		this.descripcion = descripcion;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
		this.reserva = reserva;
		this.habitacion = habitacion;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getID_empleado() {
		return ID_empleado;
	}


	public void setID_empleado(int iD_empleado) {
		ID_empleado = iD_empleado;
	}


	public int getID_reserva() {
		return ID_reserva;
	}


	public void setID_reserva(int iD_reserva) {
		ID_reserva = iD_reserva;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Reserva getReserva() {
		return reserva;
	}


	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}


	public Habitaciones getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(Habitaciones habitacion) {
		this.habitacion = habitacion;
	}



	
	

	
	

    
}
