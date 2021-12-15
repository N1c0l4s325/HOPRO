/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



public class Persons {
  private  int ID;
  private  int identification;
  private String typeIdentification;
  private  String name;
  private  String surName;  
  private  String address;
  private  String phone;
  private  String email;
  private  String country;
  private int Id_tipo;
  private TipoPersona tipo;
public Persons() {
	
}
public Persons(int iD, int identification, String typeIdentification, String name, String surName, String address,
		String phone, String email, String country, int id_tipo, TipoPersona tipo) {
	
	ID = iD;
	this.identification = identification;
	this.typeIdentification = typeIdentification;
	this.name = name;
	this.surName = surName;
	this.address = address;
	this.phone = phone;
	this.email = email;
	this.country = country;
	Id_tipo = id_tipo;
	this.tipo = tipo;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getIdentification() {
	return identification;
}
public void setIdentification(int identification) {
	this.identification = identification;
}
public String getTypeIdentification() {
	return typeIdentification;
}
public void setTypeIdentification(String typeIdentification) {
	this.typeIdentification = typeIdentification;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurName() {
	return surName;
}
public void setSurName(String surName) {
	this.surName = surName;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public int getId_tipo() {
	return Id_tipo;
}
public void setId_tipo(int id_tipo) {
	Id_tipo = id_tipo;
}
public TipoPersona getTipo() {
	return tipo;
}
public void setTipo(TipoPersona tipo) {
	this.tipo = tipo;
}



    
}
