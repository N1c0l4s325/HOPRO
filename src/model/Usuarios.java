/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



public class Usuarios {
   private int ID;
   private int ID_persons;
   private String user;
   private String password;
   private int ID_acceso;
   private Persons persona;
   private TipoAcceso acceso;
   
public Usuarios() {
	
}

public Usuarios(int iD, int iD_persons, String user, String password, int iD_acceso, Persons persona,
		TipoAcceso acceso) {
	super();
	ID = iD;
	ID_persons = iD_persons;
	this.user = user;
	this.password = password;
	ID_acceso = iD_acceso;
	this.persona = persona;
	this.acceso = acceso;
}

public int getID() {
	return ID;
}

public void setID(int iD) {
	ID = iD;
}

public int getID_persons() {
	return ID_persons;
}

public void setID_persons(int iD_persons) {
	ID_persons = iD_persons;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getID_acceso() {
	return ID_acceso;
}

public void setID_acceso(int iD_acceso) {
	ID_acceso = iD_acceso;
}

public Persons getPersona() {
	return persona;
}

public void setPersona(Persons persona) {
	this.persona = persona;
}

public TipoAcceso getAcceso() {
	return acceso;
}

public void setAcceso(TipoAcceso acceso) {
	this.acceso = acceso;
}








   
   





	
}












   
   
	

    

   
    

