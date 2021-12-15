package model;

import java.io.InputStream;

public class archivoPdf {

private	int id;
private	InputStream archivo;
private	byte[] archivoPdf;
private	int idFactura;
private	Factura factura;

	
	
	
	
	public archivoPdf() {
		
	}





	public archivoPdf(int id, InputStream archivo, byte[] archivoPdf, int idFactura, Factura factura) {
		super();
		this.id = id;
		this.archivo = archivo;
		this.archivoPdf = archivoPdf;
		this.idFactura = idFactura;
		this.factura = factura;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public InputStream getArchivo() {
		return archivo;
	}





	public void setArchivo(InputStream archivo) {
		this.archivo = archivo;
	}





	public byte[] getArchivoPdf() {
		return archivoPdf;
	}





	public void setArchivoPdf(byte[] archivoPdf) {
		this.archivoPdf = archivoPdf;
	}





	public int getIdFactura() {
		return idFactura;
	}





	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}





	public Factura getFactura() {
		return factura;
	}





	public void setFactura(Factura factura) {
		this.factura = factura;
	}



	

	
}
