package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.Habitaciones;

public class HabitacionesDao {
	Connection con = Conexion.getConexion("hopro", "root", "");

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	int numeroHabitaciones = 0;
		
	public HabitacionesDao() {
		
	}
	
	public List<Habitaciones> getSelect() throws SQLException, Exception {
		Habitaciones habitacion = null;
		List<Habitaciones> ltHcion = new ArrayList<>();
		String sql = "SELECT * FROM habitaciones ORDER BY nivel, numHab";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				habitacion = new Habitaciones();
				habitacion.setID(rs.getInt("ID"));
				habitacion.setNivel(rs.getString("nivel"));
				habitacion.setNumHab(rs.getInt("numHab"));
				habitacion.setCapacidad(rs.getInt("capacidad"));
				habitacion.setDescripcion(rs.getString("Descripcion"));				
				habitacion.setTipoHabitacion(rs.getString("tipoHabitacion").toUpperCase());
				habitacion.setPrecio(rs.getDouble("precio"));
				habitacion.setEstado(rs.getString("estado").toUpperCase());
				

				ltHcion.add(habitacion);
				
				
			}
			return ltHcion;
	}
	
	public List<Habitaciones> habitacionesPorCliente(int documento) throws SQLException, Exception {
		Habitaciones habitacion = null;
		List<Habitaciones> ltHcion = new ArrayList<>();
		String sql = "SELECT habitaciones.ID, habitaciones.nivel, habitaciones.numHab FROM habitaciones "
				+ "INNER JOIN reservas ON habitaciones.ID = reservas.ID_Habitacion "
				+ "INNER JOIN persons ON persons.ID = reservas.ID_persona WHERE persons.identification ='"+documento+"' AND reservas.estado <> 'pagada' ";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				habitacion = new Habitaciones();
				habitacion.setID(rs.getInt("ID"));
				habitacion.setNivel(rs.getString("nivel"));
				habitacion.setNumHab(rs.getInt("numHab"));
			
				ltHcion.add(habitacion);
				
				
			}
			return ltHcion;
	}
	public List<Habitaciones> getSelectIdHabitacion(String estado) throws SQLException, Exception {

		Habitaciones habitacion = null;	
		List<Habitaciones> ltHcion = new ArrayList<>();
		String sql = "SELECT ID, nivel, numHab FROM habitaciones WHERE estado = ? ORDER BY nivel, numHab ASC ";

			ps = con.prepareStatement(sql);
			ps.setString(1, estado);
			rs = ps.executeQuery();

			while (rs.next()) {
				habitacion = new Habitaciones();
				habitacion.setID(rs.getInt("ID"));
				habitacion.setNivel(rs.getString("nivel"));
				habitacion.setNumHab(rs.getInt("numHab"));
				ltHcion.add(habitacion);
								
			}	
		
		return ltHcion;
	}
	
	
	
	public int cantidadHabitaciones(String dato) throws SQLException, Exception {

		  String sql = "SELECT * FROM habitaciones WHERE estado = ?" ;
		  int numeroHabitaciones = 0;
			ps = con.prepareStatement(sql);
			ps.setString(1, dato);
			rs = ps.executeQuery();

			while (rs.next()) {

				numeroHabitaciones ++;
				}		
		
		return numeroHabitaciones;
	}
	
	public Habitaciones getSelectId(int ID) throws SQLException, Exception {
		Habitaciones habitacion = null;
	
		String sql = "SELECT * FROM habitaciones WHERE ID = " + ID;

		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				habitacion = new Habitaciones();
				habitacion.setID(rs.getInt(1));
				habitacion.setNivel(rs.getString(2));
				habitacion.setNumHab(rs.getInt(3));
				habitacion.setCapacidad(rs.getInt(4));
				habitacion.setDescripcion(rs.getString(5));				
				habitacion.setTipoHabitacion(rs.getString(6));
				habitacion.setPrecio(rs.getDouble(7));
				habitacion.setEstado(rs.getString(8));				

					
				
			}

		

		return habitacion;
	}
	
	public List<Habitaciones> getSelect(String tipo_habitacion) throws SQLException, Exception {
		Habitaciones habitacion;
		List<Habitaciones> ltHcion = new ArrayList<>();
		String sql = "SELECT * FROM habitaciones WHERE tipoHabitacion = '" + tipo_habitacion +"'" ;

		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				habitacion = new Habitaciones();
				habitacion.setID(rs.getInt(1));
				habitacion.setNivel(rs.getString(2).toUpperCase());
				habitacion.setNumHab(rs.getInt(3));
				habitacion.setCapacidad(rs.getInt(4));
				habitacion.setDescripcion(rs.getString(5));				
				habitacion.setTipoHabitacion(rs.getString(6).toUpperCase());
				habitacion.setPrecio(rs.getDouble(7));
				habitacion.setEstado(rs.getString(8).toUpperCase());
				

				ltHcion.add(habitacion);
				
				
			}

		
		return ltHcion;
	}
	
	
	public void getInsert(Habitaciones habitacion) throws SQLException, Exception {
	
		String sql = "INSERT INTO habitaciones (ID, numeroPiso, numHab, capacidad, Descripcion, tipoHabitacion, precio, images, estado)"
				+ " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ? ) ";

		
			ps = con.prepareStatement(sql);
			
			ps.setString(1, habitacion.getNivel());
			ps.setInt(2, habitacion.getNumHab());
			ps.setInt(3, habitacion.getCapacidad());
			ps.setString(4, habitacion.getDescripcion());
			ps.setString(5, habitacion.getTipoHabitacion());
			ps.setDouble(6, habitacion.getPrecio());
			ps.setString(7, habitacion.getEstado());
			
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

		
		
	}

	
		
	public void getUpdate(Habitaciones habitacion) throws SQLException, Exception {

		String sql = "UPDATE habitaciones SET numeroPiso = ?, numHab = ?, capacidad = ?, Descripcion = ?," + 
		
					 "tipoHabitacion = ?, precio = ?, estado = ? WHERE ID = ?";

		
			ps = con.prepareStatement(sql);
			ps.setString(1, habitacion.getNivel());
			ps.setInt(2, habitacion.getNumHab());
			ps.setInt(3, habitacion.getCapacidad());
			ps.setString(4, habitacion.getDescripcion());
			ps.setString(5, habitacion.getTipoHabitacion());
			ps.setDouble(6, habitacion.getPrecio());
			ps.setString(7,habitacion.getEstado());
			ps.setInt(8,habitacion.getID());
			
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

		

	}
	
	
	public void cambiarEstadoHabitacion(String estado, int ID) throws SQLException, Exception {
		
		String sql = "UPDATE habitaciones SET estado = ? WHERE ID = ?";

		
			ps = con.prepareStatement(sql);
			ps.setString(1,estado);
			ps.setInt(2,ID);			
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();
		
		
	}
	
	
	public void getDelete(int id) throws SQLException, Exception {
		String sql = "DELETE FROM habitaciones WHERE ID = " + id;

		
			ps = con.prepareStatement(sql);
			ps.executeUpdate();

			ps.close();
			Conexion.offConexion();

	}
	

}
