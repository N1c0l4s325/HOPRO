package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.Empleados;
import model.Persons;

public class EmpleadoDao {

	Connection con = Conexion.getConexion("hopro", "root", "");
	PreparedStatement ps;
	ResultSet rs;
	static int cantidadEmpleados = 0;
	
	
	public EmpleadoDao() {
		
	}
		
	public List<Empleados> getSelect() throws SQLException, Exception {

		List<Empleados> arrayRegistros = new ArrayList<>();
		 Empleados empleado = new Empleados();
		String sql = "SELECT e.ID, p.identification, p.name, p.surName, e.seguroSocial, e.cargo, e.fechaContrato, e.salario "
				+    "FROM empleado e INNER JOIN persons p on e.ID_persona = p.ID"
				+ 	  "ORDER BY ID DESC";
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				empleado.setID(rs.getInt("ID"));
				empleado.setPersona(new Persons());
				empleado.getPersona().setIdentification(rs.getInt("identification"));				
				empleado.getPersona().setName(rs.getString("name"));				
				empleado.getPersona().setSurName(rs.getString("surName"));
				empleado.setSeguroSocial(rs.getString("seguroSocial"));
				empleado.setCargo(rs.getString("cargo"));
				empleado.setFechaContrato(rs.getString("fechaContrato"));
				empleado.setSalario(rs.getDouble("salario"));				
				
				arrayRegistros.add(empleado);
			

			
			}

		

		return arrayRegistros;
	}
	
	public int extraerIdEmpleado(String id) throws SQLException, Exception {

		 int idEm = 0;
		 
		String sql = "SELECT e.ID, FROM empleado e INNER JOIN persons p on e.ID_persona = p.ID"
				+ 	  " WHERE p.ID = ? ORDER BY ID DESC";
		
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				idEm = rs.getInt("ID");
						
			}

		

		return idEm;
	}
	
	
	public List<Empleados> getSelectId(int ID) throws SQLException, Exception  {

		List<Empleados> arrayRegistros = new ArrayList<>();
		 Empleados empleado = new Empleados();
		String sql = "SELECT * FROM empleado ID = " + ID;
		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				empleado.setID(rs.getInt(1));
				empleado.setID_persona(rs.getInt(2));
				empleado.setSeguroSocial(rs.getString(3));
				empleado.setCargo(rs.getString(4));
				empleado.setFechaContrato(rs.getString(5));
				empleado.setSalario(rs.getDouble(6));
				arrayRegistros.add(empleado);
				cantidadEmpleados ++;
			
			}

		

		return arrayRegistros;
	}
	public  List<Empleados> getSelect(String dato) throws SQLException, Exception{

		List<Empleados> arrayRegistros = new ArrayList<>();
		Empleados empleado = new Empleados();
		String sql = "SELECT e.ID, p.identification, p.name, p.surName, e.seguroSocial, e.cargo, e.fechaContrato, e.salario "
				+    "FROM empleado e INNER JOIN persons p on e.ID_persona = p.ID"
				+ 	  "WHERE p.identification = '" + dato + "'OR seguroSocial='"+ dato +"' ORDER BY ID DESC";

		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				empleado.setID(rs.getInt("ID"));
				empleado.setPersona(new Persons());
				empleado.getPersona().setIdentification(rs.getInt("identification"));				
				empleado.getPersona().setName(rs.getString("name"));				
				empleado.getPersona().setSurName(rs.getString("surName"));
				empleado.setSeguroSocial(rs.getString("seguroSocial"));
				empleado.setCargo(rs.getString("cargo"));
				empleado.setFechaContrato(rs.getString("fechaContrato"));
				empleado.setSalario(rs.getDouble("salario"));		
								
				cantidadEmpleados ++;
			}

		
		return arrayRegistros;
	}
	
	
	public void getInsert(Empleados objectEmp) throws SQLException, Exception {

		String sql = "INSERT INTO empleado (ID, ID_persona, seguroSocial, cargo, fechaContrato, salario)"
				+ " VALUES(null, ?, ?, ?, ?, ? ) ";

	
			ps = con.prepareStatement(sql);
			ps.setInt(1, objectEmp.getID_persona());
			ps.setString(2, objectEmp.getSeguroSocial());
			ps.setString(3, objectEmp.getCargo());
			ps.setString(4, objectEmp.getFechaContrato());
			ps.setDouble(5, objectEmp.getSalario());

			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

	}

	public void getUpdate(Empleados objectEmp) throws SQLException, Exception {

		String sql = "UPDATE empleado SET ID_persona = ?, seguroSocial = ?, añosAntiguedad = ?, cargo = ?, fechaContrato = ?, salario = ? "
					+ "WHERE ID = ?";

	
		ps = con.prepareStatement(sql);
		ps.setInt(1, objectEmp.getID_persona());
		ps.setString(2, objectEmp.getSeguroSocial());
		ps.setString(3, objectEmp.getCargo());
		ps.setString(4, objectEmp.getFechaContrato());
		ps.setDouble(5, objectEmp.getSalario());
		ps.setInt(6, objectEmp.getID());

			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

			
	}

	public void getDelete(int id) throws SQLException, Exception {
		String sql = "DELETE FROM empleado WHERE ID = " + id;
		
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			Conexion.offConexion();

			

		
	}

		
	public static int cantRegistro() {
		return cantidadEmpleados;
	}

}
