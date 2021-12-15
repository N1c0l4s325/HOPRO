/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Consumos {
	private int ID;
	private int ID_producto;
	private int ID_reserva;
	private String fechaHoraPedido; 
	private int cantidad;
	private double saldo;
	private  String estado;
	private Productos producto;
	private Reserva reserva;
	
	
	public Consumos() {
		
	}






	public Consumos(int iD, int iD_producto, int iD_reserva, String fechaHoraPedido, int cantidad, double saldo,
			String estado, Productos producto, Reserva reserva) {
		super();
		ID = iD;
		ID_producto = iD_producto;
		ID_reserva = iD_reserva;
		this.fechaHoraPedido = fechaHoraPedido;
		this.cantidad = cantidad;
		this.saldo = saldo;
		this.estado = estado;
		this.producto = producto;
		this.reserva = reserva;
	}






	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public int getID_producto() {
		return ID_producto;
	}



	public void setID_producto(int iD_producto) {
		ID_producto = iD_producto;
	}



	public int getID_reserva() {
		return ID_reserva;
	}



	public void setID_reserva(int iD_reserva) {
		ID_reserva = iD_reserva;
	}







	public String getFechaHoraPedido() {
		return fechaHoraPedido;
	}






	public void setFechaHoraPedido(String fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}






	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public double getSaldo() {
		return saldo;
	}



	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Productos getProducto() {
		return producto;
	}



	public void setProducto(Productos producto) {
		this.producto = producto;
	}






	public Reserva getReserva() {
		return reserva;
	}






	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	

	
	
	
	
    
}
