/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;




public class Empleados {
	private int ID;
	private int ID_persona;
	private String seguroSocial;
	private String cargo;
	private String fechaContrato;
	private Double salario;
	private Persons persona;
	
	
	public Empleados() {
		
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getID_persona() {
		return ID_persona;
	}


	public void setID_persona(int iD_persona) {
		ID_persona = iD_persona;
	}


	public String getSeguroSocial() {
		return seguroSocial;
	}


	public void setSeguroSocial(String seguroSocial) {
		this.seguroSocial = seguroSocial;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getFechaContrato() {
		return fechaContrato;
	}


	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
	}


	public Double getSalario() {
		return salario;
	}


	public void setSalario(Double salario) {
		this.salario = salario;
	}


	public Persons getPersona() {
		return persona;
	}


	public void setPersona(Persons persona) {
		this.persona = persona;
	}
	
	
	

}
