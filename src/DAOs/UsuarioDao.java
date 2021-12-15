package DAOs;

import java.sql.*;
import java.util.*;
import config.Conexion;
import model.Usuarios;
import model.Persons;
import model.TipoAcceso;

public class UsuarioDao {

Connection cnx =  Conexion.getConexion("hopro", "root", "");
PreparedStatement ps = null;
ResultSet rs = null;
public UsuarioDao() {
	
}

public List<Usuarios> listAccess() throws SQLException, Exception{
	List<Usuarios> ltAc = new ArrayList<>();
	
	String sql = "SELECT * FROM access";
		ps = cnx.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			Usuarios usu = new Usuarios(); 
			usu.setID(rs.getInt(1));
			usu.setID_persons(rs.getInt(2));
			usu.setUser(rs.getString(3));
			usu.setPassword(rs.getString(4));
			ltAc.add(usu);
		}
		ps.close();
		
	return ltAc;

}

public Usuarios validar(Usuarios usuario) throws SQLException, Exception {	
	
	Usuarios usu = new Usuarios();;
	String sql = "SELECT u.ID, u.user, u.password, p.ID AS IdPersona , p.name, p.surName, ac.tipoAcceso "
			+ "FROM usuario AS u  INNER JOIN acceso AS ac ON u.ID_tipoAccess = ac.ID "
			+ "INNER JOIN persons AS p ON u.ID_persons = p.ID"
			+ " WHERE u.user = '" + usuario.getUser() + "' AND u.password = '" + usuario.getPassword() + "' OR u.ID ='" + usuario.getID() + "'";
	
	ps = cnx.prepareStatement(sql);
	rs = ps.executeQuery();
	
	while (rs.next()) {
		
		usu.setID(rs.getInt("ID"));
		usu.setUser(rs.getString("user"));
		usu.setPassword(rs.getString("password"));
		usu.setPersona(new Persons());
		usu.getPersona().setID(rs.getInt("IdPersona"));
		usu.getPersona().setName(rs.getString("name"));
		usu.getPersona().setSurName(rs.getString("surName"));
		usu.setAcceso(new TipoAcceso());
		usu.getAcceso().setTipoAccess(rs.getString("tipoAcceso"));
	}
	rs.close();
	ps.close();
	Conexion.offConexion();
	
	return usu;
	
		
}

public void add(Usuarios usu) throws SQLException, Exception  {
	
	
	String sql = "INSERT INTO usuario(ID, ID_persons, user, password, tipoAcceso) VALUES (null, ?, ?, ?, ?)";
	
		cnx = Conexion.getConexion("hopro", "root", "");
		ps = cnx.prepareStatement(sql);
		ps.setInt(1, usu.getID_persons());
		ps.setString(2, usu.getUser());
		ps.setString(3, usu.getPassword());
		ps.setInt(4, usu.getID_acceso());
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();
		
		
		
}



public void getUpdate (Usuarios usu) throws SQLException, Exception {
		
	String sql = "UPDATE usuario SET user = ?, password = ? WHERE ID = ? ";
	
	cnx = Conexion.getConexion("hopro", "root", "");
	ps = cnx.prepareStatement(sql);
	ps.setString(1, usu.getUser());
	ps.setString(2, usu.getPassword());
	ps.setInt(3, usu.getID());
	ps.executeUpdate();
	ps.close();
	Conexion.offConexion();
		
	

}

public void deleteReg(int id) throws SQLException, Exception {
		String sql = "DELETE FROM usuario WHERE id = ?" ;	
		cnx = Conexion.getConexion("hopro", "root", "");
		ps = cnx.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();
	
	
				
	}



}
