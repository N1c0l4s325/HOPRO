package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.TipoAcceso;

public class AccesoDao {

	public AccesoDao() {
		
	}
Connection con = Conexion.getConexion("hopro", "root", "");	
PreparedStatement ps;
ResultSet rs;

	
public List<TipoAcceso> getSelect() throws SQLException, Exception
{
	TipoAcceso tac = new TipoAcceso();
	List<TipoAcceso> tipoAcceso = new ArrayList<>();
	String sql = "SELECT * FROM tipoaccesso WHERE tipoAcceso <> 'cliente'";
	
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
		
		tac.setID(rs.getInt("ID"));	
		tac.setTipoAccess(rs.getString("tipoAcceso"));	
		tipoAcceso.add(tac);			
		}

	
	
	return tipoAcceso;
		
}	

public List<TipoAcceso> getSelect(int id) throws SQLException, Exception{
	TipoAcceso tac = new TipoAcceso();
	List<TipoAcceso> tipoAcceso = new ArrayList<>();
	String sql = "SELECT * FROM tipoaccesso WHERE tipoAcceso <> 'cliente'";
	
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
		
		tac.setID(rs.getInt("ID"));	
		tac.setTipoAccess(rs.getString("tipoAcceso"));	
		tipoAcceso.add(tac);			
		}

	
	
	return tipoAcceso;
		
		
}	
public void getInser(TipoAcceso tipoAcc) throws SQLException, Exception 
{
	String sql = "INSERT INTO tipoacceso (ID, tipoAcceso) VALUES (null, ? )";

	
	ps = con.prepareStatement(sql);
	ps.setInt(1,tipoAcc.getID());
	ps.setString(2, tipoAcc.getTipoAccess());	
	ps.executeUpdate();
	ps.close();
	Conexion.offConexion();
}
	
public void getUpdate(TipoAcceso tipoAcc)throws SQLException, Exception
{
	String sql = "UPDATE reclamo SET tipoAccess = ? WHERE ID = ?";  
	
	ps = con.prepareStatement(sql);
	ps.setString(1, tipoAcc.getTipoAccess());
	ps.setInt(2, tipoAcc.getID());
	
	ps.executeUpdate();
	ps.close();
	Conexion.offConexion();
}	

public void getDelete(int id) throws SQLException, Exception
{
	
	String sql = "DELETE FROM tipoacceso WHRE ID = ? ";

	
	ps = con.prepareStatement(sql);
	ps.setInt(1, id);
	ps.executeUpdate();

	ps.close();
	Conexion.offConexion();

	
}
	
	
	
	
	
	
	
	
}
