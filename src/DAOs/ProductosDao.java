package DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.Productos;

public class ProductosDao {

Connection con = Conexion.getConexion("hopro", "root", "");	
PreparedStatement ps = null;
ResultSet rs = null;

static Double precioVenta = 0.0;
	
public ProductosDao() {
		
	}
	
	public List<Productos> getSelect() throws SQLException, Exception {

		List<Productos> arrayProductos = new ArrayList<>();		
		String sql = "SELECT * FROM productos";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			
			Productos pd = new Productos();
			pd.setID(rs.getInt("ID"));
			pd.setTipoAlimento(rs.getString("TipoAlimento"));
			pd.setMarca(rs.getString("marca"));
			pd.setDescripcion(rs.getString("descripcion"));
			pd.setPrecioVenta(rs.getDouble("precioVenta"));
			pd.setStock(rs.getInt("stock"));
			arrayProductos.add(pd);

		}

		return arrayProductos;
	}
	public List<Productos> getSelect(String dato) throws SQLException, Exception {

		List<Productos> arrayProductos = new ArrayList<>();
		Productos pd = new Productos();
		String sql = "SELECT * FROM productos WHERE marca LIKE '%"+ dato +"%' OR descripcion LIKE '%" + dato + "%' OR TipoAlimento LIKE '%" + dato + "%'";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while (rs.next()) {

			pd.setID(rs.getInt("ID"));
			pd.setTipoAlimento(rs.getString("TipoAlimento"));
			pd.setMarca(rs.getString("marca"));
			pd.setDescripcion(rs.getString("descripcion"));
			pd.setPrecioVenta(rs.getDouble("precioVenta"));
			pd.setStock(rs.getInt("stock"));
			arrayProductos.add(pd);

		}

		return arrayProductos;
	}
	
	public Productos buscarProducto(String id) throws SQLException, Exception {

		Productos pd = null;
		String sql = "SELECT * FROM productos WHERE ID='"+ id +"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			pd = new Productos();
			pd.setID(rs.getInt("ID"));
			pd.setTipoAlimento(rs.getString("TipoAlimento"));
			pd.setMarca(rs.getString("marca"));
			pd.setDescripcion(rs.getString("descripcion"));
			pd.setPrecioVenta(rs.getDouble("precioVenta"));
			pd.setStock(rs.getInt("stock"));
		}

		return pd;
	}

	public Double precioVenta(int ID) throws SQLException, Exception {

		String sql = "SELECT precioVenta FROM productos WHERE ID ='" + ID + "'" ;

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {

			precioVenta = rs.getDouble("precioVenta");

		}

		return precioVenta;

	}
	
	public void disminuirStock(int stockFinal, int ID) throws SQLException, Exception {
		
		String sql = "UPDATE productos SET stock = ? WHERE ID = ? ";
		ps = con.prepareStatement(sql);
		ps.setInt(1, stockFinal);
		ps.setInt(2, ID);
		
		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();
	}

	public void getInsert(Productos objectProduc) throws SQLException, Exception {

		String sql = "INSERT INTO productos(marca, TipoAlimento, descripcion, precioVenta) VALUES(null, ?, ?, ?, ? )";

		ps = con.prepareStatement(sql);

		ps.setString(1, objectProduc.getMarca());
		ps.setString(2, objectProduc.getTipoAlimento());
		ps.setString(3, objectProduc.getDescripcion());
		ps.setDouble(4, objectProduc.getPrecioVenta());

		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

	}

	public void getUpdate(Productos producto) throws SQLException, Exception {

		String sql = "UPDATE productos SET marca = ?, descripcion = ?, precioVenta = ? WHERE ID = ? ";

		ps = con.prepareStatement(sql);
		ps.setString(1, producto.getMarca());
		ps.setString(2, producto.getTipoAlimento());
		ps.setString(3, producto.getDescripcion());
		ps.setDouble(4, producto.getPrecioVenta());

		ps.executeUpdate();
		ps.close();
		Conexion.offConexion();

	}

	public void getDelete(int id) throws SQLException, Exception {
		String sql = "DELETE FROM productos WHERE ID = " + id;

		ps = con.prepareStatement(sql);
		ps.executeUpdate();

		ps.close();
		Conexion.offConexion();

	}

}
