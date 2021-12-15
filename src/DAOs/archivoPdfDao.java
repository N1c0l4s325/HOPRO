package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import config.Conexion;
import model.Factura;
import model.Persons;

import model.archivoPdf;

public class archivoPdfDao {

	public archivoPdfDao() {
		
	}
	
	Connection con = Conexion.getConexion("hopro", "root", "");	
	PreparedStatement ps;
	ResultSet rs;
	
	public archivoPdf getSelect(int documento) throws SQLException, Exception
	{
		
		String sql = "SELECT pdf.ID, pdf.archivo, factura.fechaEmision, factura.estado FROM archivopdf AS pdf "
				+ "INNER JOIN factura ON pdf.ID_factura = factura.ID "
				+ "INNER JOIN reservas ON factura.ID_reserva = reservas.ID "
				+ "INNER JOIN persons ON reservas.ID_persona = persons.ID "
				+ "WHERE persons.identification = '"+ documento +"'";
			
			archivoPdf pdf = new archivoPdf();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				pdf.setId(rs.getInt("ID"));
				pdf.setArchivoPdf(rs.getBytes("archivo"));
				pdf.setIdFactura(rs.getInt("ID_Factura"));
				pdf.setFactura(new Factura());
				pdf.getFactura().setFechaEmision(rs.getString("fechaEmision"));				
				pdf.getFactura().setEstado(rs.getString("estado"));
			}

				
		return pdf;
			
	}	

	public void getInsert(archivoPdf pdf) throws SQLException, Exception 
	{
		String sql = "INSERT INTO archivopdf(ID, archivo, ID_factura) VALUES (null, ?, ?)";

		
		ps = con.prepareStatement(sql);
		ps.setBytes(1, pdf.getArchivoPdf());
		ps.setInt(2, pdf.getIdFactura());
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();
	}
		
	public void getUpdate(archivoPdf pdf)throws SQLException, Exception
	{
		String sql = "UPDATE reclamo SET tipoAccess = ? WHERE ID = ?";  
		
		ps = con.prepareStatement(sql);
		ps.setBytes(1, pdf.getArchivoPdf());
		ps.setInt(2, pdf.getIdFactura());
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();
	}	

	public void getDelete(int id) throws SQLException, Exception
	{
		
		String sql = "DELETE FROM archivopdf WHERE ID = " + id ;

		
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();

		ps.close();
		Conexion.offConexion();

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
