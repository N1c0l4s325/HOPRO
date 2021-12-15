package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Conexion;

public class TipoPersonaDao {

	Connection con = Conexion.getConexion("hopro", "root", "");	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
public int getTipoPersona(String dato)  throws SQLException, Exception {
	
	int ID = 0;

	String sql = "SELECT ID FROM tipopersona WHERE tipo = '" + dato + "'";

	ps = con.prepareStatement(sql);
	rs = ps.executeQuery();

	while (rs.next()) {
		ID = rs.getInt("ID");
		

	}

	return ID;	
	
}
	
	
	
}
