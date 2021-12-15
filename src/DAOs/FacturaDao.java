package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.*;




public class FacturaDao {
	
Connection con = Conexion.getConexion("hopro", "root", "");	
PreparedStatement ps = null;
ResultSet rs = null;

	
public FacturaDao() {
		
	}

public String GenerarNumero() throws SQLException, Exception {
	String numero = "";
	String sql = "SELECT MAX(numComprobante) FROM factura";
	ps= con.prepareStatement(sql);
	rs = ps.executeQuery();
	while (rs.next()) {
		numero = rs.getString(1);
		}
	return numero;
}	
		


public void getInsert(Factura objectfactura) throws SQLException, Exception {

String sql = "INSERT INTO factura(ID, ID_reserva, numComprobante, fechaEmision, formaPago, estado)"
			+ "VALUES (null, ?, ?, ?, ?, ?)";


		ps = con.prepareStatement(sql);

		ps.setInt(1, objectfactura.getID_reserva());
		ps.setString(2, objectfactura.getNumComprobante());
		ps.setString(3, objectfactura.getFechaEmision());
		ps.setString(4, objectfactura.getFormaPago());
		ps.setString(5, objectfactura.getEstado());

		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

	

}


public void getUpdate(Factura objectfactura) throws SQLException, Exception {

	String sql = "UPDATE factura SET  ID_reserva = ?, numComprobante = ?, fechaEmision = ?"
			+ "formaPago = ?, estado = ? WHERE ID = ?";

	
		ps = con.prepareStatement(sql);		
		ps.setInt(2, objectfactura.getID_reserva());	
		ps.setString(4, objectfactura.getNumComprobante());
		ps.setString(5, objectfactura.getFechaEmision());
		ps.setString(6, objectfactura.getFormaPago());
		ps.setString(7, objectfactura.getEstado());
		ps.setInt(8, objectfactura.getID());
		
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();
	}
public void updateEstado(Factura objectfactura) throws SQLException, Exception {

	String sql = "UPDATE factura SET estado = ? WHERE ID = ?";

	
		ps = con.prepareStatement(sql);
		ps.setInt(2, objectfactura.getID());
		
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

		
	

}

public void getDelete(int id) throws SQLException, Exception {
	String sql = "DELETE FROM factura WHERE ID = ? ";
	
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();

		ps.close();
		Conexion.offConexion();
	
}



}
