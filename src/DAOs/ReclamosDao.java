package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.Habitaciones;
import model.Persons;
import model.Reclamos;
import model.Reserva;

public class ReclamosDao {

public ReclamosDao() {
		
	}
	
Connection con = Conexion.getConexion("hopro", "root", "");	
PreparedStatement ps;
ResultSet rs;

	
public List<Reclamos> getSelect(Reserva reserva) throws SQLException, Exception {

	
	List<Reclamos> listaReclamos = new ArrayList <>();	
	
	String sql = "SELECT rm.ID, rm.ID_reserva, rm.ID_empleado, rm.descripcion, rm.HoraFechIngreso, rm.estado, h.nivel, h.numHab, rv.estado AS reserva"
			+ " FROM reclamo AS rm "
			+ "INNER JOIN reservas AS rv ON rm.ID_reserva = rv.ID INNER JOIN habitaciones AS h ON rv.ID_Habitacion = h.ID  "
			+ "WHERE rm.ID_reserva = ?";
	
	
	
		ps = con.prepareStatement(sql);
		ps.setInt(1, reserva.getId());
		rs = ps.executeQuery();

		while (rs.next()) {
			Reclamos reclamo = new Reclamos();
			reclamo.setID(rs.getInt("ID"));
			reclamo.setID_empleado(rs.getInt("ID_empleado"));
			reclamo.setID_reserva(rs.getInt("ID_reserva"));
			reclamo.setDescripcion(rs.getString("descripcion"));
			reclamo.setFechaIngreso(rs.getString("HoraFechIngreso"));
			reclamo.setEstado(rs.getString("estado"));
			reclamo.setReserva(new Reserva());
			reclamo.getReserva().setEstado(rs.getString("reserva"));
			listaReclamos.add(reclamo);
		}

	

	return listaReclamos;
}
	


public Reclamos  getSelect(int ID) throws SQLException, Exception {

	
		List<Reclamos> listaReclamos = new ArrayList<>();
		String sql = "SELECT rm.ID, rm.ID_reserva, rm.ID_empleado, rm.descripcion, rm.HoraFechIngreso, rm.estado, h.nivel, h.numHab, rv.estado AS reserva"
				+ " FROM reclamo AS rm "
				+ "INNER JOIN reservas AS rv ON rm.ID_reserva = rv.ID INNER JOIN habitaciones AS h ON rv.ID_Habitacion = h.ID  "
				+ "WHERE rm.ID = ?";
		
			ps = con.prepareStatement(sql);
			ps.setInt(1, ID);
			rs = ps.executeQuery();
			Reclamos reclamo = new Reclamos();
			while (rs.next()) {			
				reclamo.setID(rs.getInt("ID"));
				reclamo.setDescripcion(rs.getString("descripcion"));			
				
				listaReclamos.add(reclamo);
				
			}

		
		return reclamo;
	}

public void getInsert(Reclamos reclamo) throws SQLException, Exception {

	String sql = "INSERT INTO reclamo (ID, ID_empleado, ID_reserva,descripcion, HoraFechIngreso, estado) VALUES (null, ?, ?, ?, ?, ? )";

		ps = con.prepareStatement(sql);
		
		ps.setInt(1,reclamo.getID_empleado());
		ps.setInt(2,reclamo.getID_reserva());
		ps.setString(3, reclamo.getDescripcion());
		ps.setString(4, reclamo.getFechaIngreso());
		ps.setString(5, reclamo.getEstado());
		
		
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

	
}
public void getUpdate(Reclamos reclamo) throws SQLException, Exception {

		String sql = "UPDATE reclamo SET ID_empleado = ?, descripcion = ? WHERE ID = ?";  
		
			ps = con.prepareStatement(sql);
			ps.setInt(1,reclamo.getID_empleado());
			ps.setString(2, reclamo.getDescripcion());
			ps.setInt(3, reclamo.getID());
			
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

			

		

	}

public void getDelete(int id) throws SQLException, Exception {
		String sql = "DELETE FROM reclamo WHERE ID = ? ";

	
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			Conexion.offConexion();

			

	}
	


}
