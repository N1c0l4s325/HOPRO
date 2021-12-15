/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author emman
 */
public class Productos {
	private int ID;
	private String marca;
	private String tipoAlimento;
	private String descripcion;
	private double precioVenta;
	private int stock;   
   
	public Productos() {
		super();
	}


	public Productos(int iD, String marca, String tipoAlimento, String descripcion, double precioVenta, int stock) {
		super();
		ID = iD;
		this.marca = marca;
		this.tipoAlimento = tipoAlimento;
		this.descripcion = descripcion;
		this.precioVenta = precioVenta;
		this.stock = stock;
	}













	public int getID() {
		return ID;
	}






	public void setID(int iD) {
		ID = iD;
	}






	public String getMarca() {
		return marca;
	}






	public void setMarca(String marca) {
		this.marca = marca;
	}






	public String getTipoAlimento() {
		return tipoAlimento;
	}






	public void setTipoAlimento(String tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}






	public String getDescripcion() {
		return descripcion;
	}






	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}






	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

	
    
    
}
