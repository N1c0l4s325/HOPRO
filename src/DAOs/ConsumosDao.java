package DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.Consumos;
import model.Productos;
import model.Reserva;


public class ConsumosDao {

	Connection con = Conexion.getConexion("hopro", "root", "");
	PreparedStatement ps;
	ResultSet rs;
	static Double totalConsumo = 0.0;

	public ConsumosDao() {

	}

	public List<Consumos> getSelect(int dato) throws SQLException, Exception {
	
		List<Consumos> listCon = new ArrayList<>();
		String sql = "SELECT c.ID, c.fechaHoraPedido, p.marca, p.descripcion, p.precioVenta, c.cantidad, c.saldo, c.cantidad, c.estado, r.ID AS IDReserva, r.estado AS estadoReserva FROM consumo AS c "
				+ "INNER JOIN productos AS p ON c.ID_producto = p.ID "
				+ "INNER JOIN reservas AS r ON c.ID_reserva = r.ID "
				+ "INNER JOIN persons AS per ON r.ID_persona = per.ID WHERE r.ID='"+dato+"' ORDER BY ID DESC";
		
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while (rs.next()) {
			Consumos cm = new Consumos();
			cm.setID(rs.getInt("ID"));
			cm.setFechaHoraPedido(rs.getString("fechaHoraPedido"));
			cm.setProducto(new Productos());
			cm.getProducto().setMarca(rs.getString("marca"));			
			cm.getProducto().setDescripcion(rs.getString("descripcion"));			
			cm.getProducto().setPrecioVenta(rs.getDouble("precioVenta"));
			cm.setCantidad(rs.getInt("cantidad"));
			cm.setSaldo(rs.getDouble("saldo"));
			cm.setEstado(rs.getString("estado"));
			cm.setReserva(new Reserva());
			cm.getReserva().setId(rs.getInt("IDReserva"));
			cm.getReserva().setEstado(rs.getString("estadoReserva"));
			listCon.add(cm);			
		}
				
		return listCon;
	}


	public void getInsert(Consumos consumo) throws SQLException, Exception {

		String sql = "INSERT INTO consumo(ID, ID_producto, ID_reserva, fechaHoraPedido, cantidad, saldo, estado)"
				+ " VALUES(null, ?, ?, ?, ?, ?, ?)";

		ps = con.prepareStatement(sql);
		ps.setInt(1, consumo.getID_producto());
		ps.setInt(2, consumo.getID_reserva());
		ps.setString(3, consumo.getFechaHoraPedido());
		ps.setDouble(4, consumo.getCantidad());
		ps.setDouble(5, consumo.getSaldo());
		ps.setString(6, consumo.getEstado());

		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

	}

	public void getUpdate(Consumos consumo) throws SQLException, Exception {

		String sql = "UPDATE consumo SET ID_producto = ?, catidad=?, saldo = ?, estado = ? WHERE ID = ?";

		ps = con.prepareStatement(sql);
		ps.setInt(1, consumo.getID_producto());
		ps.setDouble(2, consumo.getCantidad());
		ps.setDouble(3, consumo.getSaldo());
		ps.setString(4, consumo.getEstado());

		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

	}

	public void getDelete(int id) throws SQLException, Exception {
		String sql = "DELETE FROM consumo WHERE ID = " + id;

		ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

	}

}
