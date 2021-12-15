package model;


public class Factura {

private	int ID;
private	int ID_reserva;
private String numComprobante;
private	String fechaEmision;
private	String formaPago;
private String estado;
private Reserva reserva;
private Consumos consumo;

public Factura() {
	
}


public Factura(int iD, int iD_reserva, String numComprobante, String fechaEmision, String formaPago, String estado, Reserva reserva,  Consumos consumo) {
	super();
	ID = iD;
	ID_reserva = iD_reserva;
	this.numComprobante = numComprobante;
	this.fechaEmision = fechaEmision;
	this.formaPago = formaPago;
	this.estado = estado;
	this.reserva = reserva;
	this.consumo = consumo;
}




public int getID() {
	return ID;
}




public void setID(int iD) {
	ID = iD;
}




public int getID_reserva() {
	return ID_reserva;
}




public void setID_reserva(int iD_reserva) {
	ID_reserva = iD_reserva;
}




public String getNumComprobante() {
	return numComprobante;
}




public void setNumComprobante(String numComprobante) {
	this.numComprobante = numComprobante;
}




public String getFechaEmision() {
	return fechaEmision;
}




public void setFechaEmision(String fechaEmision) {
	this.fechaEmision = fechaEmision;
}




public String getFormaPago() {
	return formaPago;
}




public void setFormaPago(String formaPago) {
	this.formaPago = formaPago;
}




public Reserva getReserva() {
	return reserva;
}




public void setReserva(Reserva reserva) {
	this.reserva = reserva;
}


public String getEstado() {
	return estado;
}


public void setEstado(String estado) {
	this.estado = estado;
}


public Consumos getConsumo() {
	return consumo;
}


public void setConsumo(Consumos consumo) {
	this.consumo = consumo;
}






}
