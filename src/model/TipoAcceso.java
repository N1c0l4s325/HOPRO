package model;

public class TipoAcceso {

	private int ID;
	private String tipoAccess;
		
public TipoAcceso() {
		
	}

public TipoAcceso(int iD, String tipoAccess) {
	
		ID = iD;
		this.tipoAccess = tipoAccess;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTipoAccess() {
		return tipoAccess;
	}

	public void setTipoAccess(String tipoAccess) {
		this.tipoAccess = tipoAccess;
	}
	
	
	
	
	
	
}
