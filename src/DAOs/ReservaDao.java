package DAOs;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import config.*;
import model.*;

public class ReservaDao {

Connection con = Conexion.getConexion("hopro", "root", "");	
PreparedStatement ps = null;
ResultSet rs = null;

	
	
public ReservaDao() {}
	

public List<Reserva> paraConsumoYfactura (int documento, int idHabitacion) throws SQLException, Exception {
	CalcularDias cd = new CalcularDias();
	List<Reserva> listaReserva = new ArrayList<>();
	
	String sql = "SELECT r.ID, r.ID_Habitacion, r.ID_persona , h.nivel, h.numHab, h.tipoHabitacion, h.precio, p.identification, p.name, p.surName, r.fechaReserva, r.fechaIngreso,r.fechaSalida, r.estado "
			+ "FROM reservas AS r "
			+ "INNER JOIN persons p on r.ID_persona = p.ID "
			+ "INNER JOIN tipopersona ON p.id_tipoPersona = tipopersona.ID "
			+ "INNER JOIN habitaciones h ON r.ID_Habitacion = h.ID "
			+ "WHERE p.identification = '"+documento+"' AND h.ID='"+idHabitacion+"' AND tipopersona.tipo = 'cliente' AND r.estado <> 'pagada' ORDER BY r.ID DESC";

	
		ps = con.prepareStatement(sql);
	
		rs = ps.executeQuery();

		while (rs.next()) {
			
			Reserva reser = new Reserva();
			reser.setId(rs.getInt("ID"));
			reser.setFechaIngreso(rs.getDate("fechaIngreso"));
			reser.setFechaReserva(rs.getDate("fechaReserva"));
			reser.setFechaSalida(rs.getDate("fechaSalida"));
			int dias = cd.calcularNoches(rs.getDate("fechaReserva"), rs.getDate("fechaSalida"));
			if (dias<0) {
				dias = dias * -1;
			}
			reser.setCantidadNoches(dias-1);
			double importe = 0.0; 
			importe = rs.getDouble("precio") * reser.getCantidadNoches();
			reser.setImporte(importe);
			reser.setHabitacion(new Habitaciones());
			reser.getHabitacion().setNivel(rs.getString("nivel").toUpperCase());
			reser.getHabitacion().setNumHab(rs.getInt("numHab"));
			reser.getHabitacion().setTipoHabitacion(rs.getString("tipoHabitacion"));
			reser.getHabitacion().setPrecio(rs.getDouble("precio"));
						
			reser.setPersona(new Persons());
			reser.getPersona().setIdentification(rs.getInt("identification"));
			reser.getPersona().setName(rs.getString("name"));
			reser.getPersona().setSurName(rs.getString("surName").toUpperCase());
			
			listaReserva.add(reser);										
		}		

	return listaReserva;
}
	


	public List<Reserva> getSelect(int integer) throws SQLException, Exception {

		List<Reserva> listaReserva = new ArrayList<>();
	
		String sql = "SELECT r.ID, r.ID_Habitacion, r.ID_persona , h.nivel, h.numHab, h.tipoHabitacion, p.identification, p.name, p.surName, r.fechaReserva, r.fechaIngreso,r.fechaSalida, r.estado "
				+ "FROM reservas AS r "
				+ "INNER JOIN persons p on r.ID_persona = p.ID "
				+ "INNER JOIN tipopersona ON p.id_tipoPersona = tipopersona.ID "
				+ "INNER JOIN habitaciones h ON r.ID_Habitacion = h.ID "
				+ "WHERE p.identification = '"+integer+"' OR r.ID ='"+integer+"' AND tipopersona.tipo = 'cliente' AND r.estado <> 'pagada' ORDER BY r.ID DESC";

		
			ps = con.prepareStatement(sql);
		
			rs = ps.executeQuery();

			while (rs.next()) {
				
				Reserva reser = new Reserva();
				reser.setId(rs.getInt("ID"));
				reser.setFechaIngreso(rs.getDate("fechaIngreso"));
				reser.setFechaReserva(rs.getDate("fechaReserva"));
				reser.setFechaSalida(rs.getDate("fechaSalida"));
			
				reser.setHabitacion(new Habitaciones());
				reser.getHabitacion().setNivel(rs.getString("nivel").toUpperCase());
				reser.getHabitacion().setNumHab(rs.getInt("numHab"));
				reser.getHabitacion().setTipoHabitacion(rs.getString("tipoHabitacion"));
				reser.getHabitacion().setPrecio(rs.getDouble("precio"));
							
				reser.setPersona(new Persons());
				reser.getPersona().setIdentification(rs.getInt("identification"));
				reser.getPersona().setName(rs.getString("name"));
				reser.getPersona().setSurName(rs.getString("surName").toUpperCase());
				
				listaReserva.add(reser);									
			}		

		return listaReserva;
	}
	
	
	
	public List<Reserva> infoParaReclamoyFactura(String dato, String estado) throws SQLException, Exception {
		
		List<Reserva> listaReserva = new ArrayList<>();
		CalcularDias cd = new CalcularDias();
		String sql = "SELECT r.ID, r.fechaIngreso, r.fechaReserva, r.fechaSalida, h.nivel, h.numHab, h.precio, h.tipoHabitacion, p.identification, p.name, p.surName "
				+ "FROM reservas AS r "
				+ "INNER JOIN persons p on r.ID_persona = p.ID "
				+ "INNER JOIN habitaciones h ON r.ID_Habitacion = h.ID "
				+ "WHERE p.identification = '" + dato + "' AND r.estado <> '"+estado+"' ORDER BY h.nivel, h.numHab";

		
			ps = con.prepareStatement(sql);
		
			rs = ps.executeQuery();

			while (rs.next()) {
				
				Reserva reser = new Reserva();
				reser.setId(rs.getInt("ID"));
				reser.setFechaIngreso(rs.getDate("fechaIngreso"));
				reser.setFechaReserva(rs.getDate("fechaReserva"));
				reser.setFechaSalida(rs.getDate("fechaSalida"));
				int dias = cd.calcularNoches(rs.getDate("fechaReserva"), rs.getDate("fechaSalida"));
				if (dias<0) {
					dias = dias * -1;
				}
				reser.setCantidadNoches(dias-1);
				double importe = 0.0; 
				importe = rs.getDouble("precio") * reser.getCantidadNoches();
				reser.setImporte(importe);
				reser.setHabitacion(new Habitaciones());
				reser.getHabitacion().setNivel(rs.getString("nivel").toUpperCase());
				reser.getHabitacion().setNumHab(rs.getInt("numHab"));
				reser.getHabitacion().setTipoHabitacion(rs.getString("tipoHabitacion"));
				reser.getHabitacion().setPrecio(rs.getDouble("precio"));
							
				reser.setPersona(new Persons());
				reser.getPersona().setIdentification(rs.getInt("identification"));
				reser.getPersona().setName(rs.getString("name"));
				reser.getPersona().setSurName(rs.getString("surName").toUpperCase());
				
				listaReserva.add(reser);									
			}		

		return listaReserva;
	}
	
	public int obtenerIdReserva(int dni, String nivel, int numero) throws SQLException, Exception {

		int id = 0;
		
		String sql = "SELECT r.ID"
				+ " FROM reservas AS r "
				+ "INNER JOIN persons p on r.ID_persona = p.ID "
				+ "INNER JOIN habitaciones h ON r.ID_Habitacion = h.ID"
				+ " WHERE p.identification = ? AND h.nivel= ?  AND numHab = ? AND r.estado <> 'Pagada' AND p.id_tipoPersona <> '3'  ORDER BY r.ID DESC";

			
			ps = con.prepareStatement(sql);
			ps.setInt(1, dni);
			ps.setString(2, nivel);
			ps.setInt(3, numero);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				id = rs.getInt("ID");
		}		

		return id;
	}
	
public List<Reserva> getSelect() throws SQLException, Exception {

		List<Reserva> list = new ArrayList<>();
		
		
		String sql = "SELECT r.ID, r.ID_Habitacion, r.ID_persona, h.nivel, h.numHab, h.tipoHabitacion, p.identification, p.name, p.surName, r.fechaReserva, r.fechaIngreso,r.fechaSalida, r.estado"
				+ " FROM reservas r"
				+ " INNER JOIN persons p on r.ID_persona = p.ID"
				+ " INNER JOIN habitaciones h ON r.ID_Habitacion = h.ID ORDER BY r.ID DESC";

			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				Reserva reser = new Reserva();
				reser.setId(rs.getInt("ID"));
				reser.setId_habitacion(rs.getInt("ID_Habitacion"));
				reser.setId_persona(rs.getInt("ID_persona"));
				reser.setFechaReserva(rs.getDate("fechaReserva"));
				reser.setFechaIngreso(rs.getDate("fechaIngreso"));
				reser.setFechaSalida(rs.getDate("fechaSalida"));
				reser.setEstado(rs.getString("estado"));
				
				
				reser.setHabitacion(new Habitaciones());
				reser.getHabitacion().setNivel(rs.getString("nivel").toUpperCase());
				reser.getHabitacion().setNumHab(rs.getInt("numHab"));
				reser.getHabitacion().setTipoHabitacion(rs.getString("tipoHabitacion"));
				
				reser.setPersona(new Persons());
				reser.getPersona().setIdentification(rs.getInt("identification"));
				reser.getPersona().setName(rs.getString("name"));
				reser.getPersona().setSurName(rs.getString("surName").toUpperCase());
				
				list.add(reser);			
			}

		return list;
	}
	
	
	

	public Reserva getSelect_Id(String dato) throws SQLException, Exception{
				
		Reserva rv = new Reserva();
		String sql = "SELECT r.ID, r.fechaReserva, r.fechaSalida, r.fechaIngreso,r.ID_Habitacion, h.nivel, h.numHab, p.ID AS ID_pers, p.name, p.surName "
				+ "FROM reservas AS r "
				+ "INNER JOIN habitaciones AS h ON r.ID_Habitacion = h.ID INNER JOIN persons AS p ON r.ID = ID_persona "
				+ "WHERE r.ID = '" + dato + "' OR p.identification='"+ dato +"' OR h.ID='"+dato+"'";
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
			
			rv.setId(rs.getInt("ID"));			
			rv.setId_habitacion(rs.getInt("ID_habitacion"));
			rv.setHabitacion(new Habitaciones());
			rv.getHabitacion().setNivel(rs.getString("nivel"));
			rv.getHabitacion().setNumHab(rs.getInt("numHab"));
			rv.setPersona(new Persons());
			rv.getPersona().setID(rs.getInt("ID_pers"));
			rv.getPersona().setName(rs.getString("name"));
			rv.getPersona().setSurName(rs.getString("surName"));
			rv.setFechaReserva(rs.getDate("fechaReserva"));
			rv.setFechaSalida(rs.getDate("fechaSalida"));
			rv.setFechaIngreso(rs.getDate("fechaIngreso"));
			}
			
		return rv;
	}
	
	public Reserva ComprobarFecha(String dato) throws SQLException, Exception{
		
		Reserva rv = new Reserva();
		String sql = "SELECT r.fechaReserva, r.fechaSalida, r.ID_Habitacion "
				+ "FROM reservas AS r "
				+ "INNER JOIN habitaciones AS h ON r.ID_Habitacion = h.ID "
				+ "WHERE h.ID='"+dato+"'";
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
			
			rv.setId_habitacion(rs.getInt("ID_habitacion"));
			rv.setFechaReserva(rs.getDate("fechaReserva"));
			rv.setFechaSalida(rs.getDate("fechaSalida"));
			
			}
			
		return rv;
	}
public int getSelect_IdReserva(Persons per) throws SQLException, Exception {
		
		int idReserva = 0;
	
		String sql = "SELECT r.ID FROM reservas AS r "
				+ "INNER JOIN persons AS p ON r.ID_persona = p.ID INNER JOIN habitaciones AS h "
				+ "WHERE p.identification = '" + per.getIdentification() +"' ";
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				
			idReserva = rs.getInt("ID");
				
			}
		
		rs.close();
		ps.close();
		Conexion.offConexion();
		
		return idReserva;
	}
public Reserva ExtraerIdParaConsumo(int id) throws SQLException, Exception {
	
	
	Reserva rv = new Reserva();
	String sql = "SELECT r.ID, r.ID_Habitacion, r.ID_persona, r.fechaReserva, r.fechaIngreso, r.fechaSalida, r.estado, h.nivel, h.numHab, p.identification, p.name, p.surName "
			+ "FROM reservas AS r "
			+ "INNER JOIN persons AS p ON r.ID_persona = p.ID "
			+ "INNER JOIN habitaciones AS h ON r.ID_Habitacion = h.ID "
			+ "INNER JOIN tipopersona ON p.id_tipoPersona = tipopersona.ID "
			+ "WHERE r.ID='" + id + "' AND tipopersona.tipo='cliente' ";
	
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			rv.setId(rs.getInt("ID"));			
			rv.setId_habitacion(rs.getInt("ID_habitacion"));
			rv.setId_persona(rs.getInt("ID_persona"));
			rv.setHabitacion(new Habitaciones());
			rv.getHabitacion().setNivel(rs.getString("nivel"));
			rv.getHabitacion().setNumHab(rs.getInt("numHab"));
			rv.setPersona(new Persons());
			rv.getPersona().setIdentification(rs.getInt("identification"));
			rv.getPersona().setName(rs.getString("name"));
			rv.getPersona().setSurName(rs.getString("surName"));
			rv.setFechaReserva(rs.getDate("fechaReserva"));
			rv.setFechaSalida(rs.getDate("fechaSalida"));
			rv.setFechaIngreso(rs.getDate("fechaIngreso"));
			
		}
	
	rs.close();
	ps.close();
	Conexion.offConexion();
	
	return rv;
}

	public void confirmarIngreso(int ID, String estado) throws SQLException, Exception {
		
		String sql = "UPDATE reservas SET fechaIngreso = now(), estado = ? WHERE ID = ?";

	
			ps = con.prepareStatement(sql);
			
			ps.setString(1, estado);
			ps.setInt(2, ID);
			
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

		
		
	}
public void actualizarEstado(String estado, String idReserva ) throws SQLException, Exception {
		
		String sql = "UPDATE reservas SET  estado ='"+ estado +"' WHERE ID = '"+ idReserva +"'";
		
			ps = con.prepareStatement(sql);
			
						
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

		
		
	}
		
	public void getInsert(Reserva reserva) throws SQLException, Exception {

		String sql = "INSERT INTO reservas(ID, usuario_ID, ID_persona, ID_Habitacion, fechaReserva, fechaIngreso, fechaSalida, estado)"
				+ "VALUES (null, ?, ?, ?, ?, null, ?, ?)";

	
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, reserva.getId_empleado());
			ps.setInt(2, reserva.getId_persona());
			ps.setInt(3, reserva.getId_habitacion());
			
			java.util.Date fechaReserva = reserva.getFechaReserva();
			java.sql.Date fechaReservaConvertida = new java.sql.Date(fechaReserva.getTime());			
			ps.setDate(4, fechaReservaConvertida);
			
			
			java.util.Date fechaSalida = reserva.getFechaSalida();
			java.sql.Date fechaSalidaConvertida = new java.sql.Date(fechaSalida.getTime());			
			ps.setDate(5, fechaSalidaConvertida);
			
			ps.setString(6, reserva.getEstado());
			
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();


	}



	public void getUpdate(Reserva reserva) throws SQLException, Exception {

		String sql = "UPDATE reservas SET ID_Habitacion = ?, fechaReserva = ?, fechaSalida = ? WHERE ID = ? ";
				
		
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, reserva.getId_habitacion());
			
			java.util.Date fechaReserva = reserva.getFechaReserva();
			java.sql.Date fechaReservaConvertida = new java.sql.Date(fechaReserva.getTime());			
			ps.setDate(2, fechaReservaConvertida);
			
			
			java.util.Date fechaSalida = reserva.getFechaSalida();
			java.sql.Date fechaSalidaConvertida = new java.sql.Date(fechaSalida.getTime());			
			ps.setDate(3, fechaSalidaConvertida);
			ps.setInt(4, reserva.getId());
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

		}
	

	public void getDelete(int id) throws SQLException, Exception {
		String sql = "DELETE FROM reservas WHERE ID = ? ";

		
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
