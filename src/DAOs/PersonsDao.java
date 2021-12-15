package DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.Conexion;
import model.Persons;
import model.TipoPersona;

public class PersonsDao {
	
Connection con;
PreparedStatement ps = null;;
ResultSet rs = null;
Persons persona;
	

public PersonsDao() {
}
public List<Persons> getSelect(String dato1) throws SQLException, Exception {
	List<Persons> pns = new ArrayList<>();
	String sql = "SELECT p.ID, p.typeIdentification, p.identification, p.name, p.surName, p.address, p.phone, p.email, p.country "
			+ "FROM persons AS p "
			+ "INNER JOIN tipopersona tp ON p.id_tipoPersona = tp.ID "
			+ "WHERE tp.tipo = '" +dato1+ "' ORDER BY ID DESC";
		con = Conexion.getConexion("hopro","root","");
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			persona = new Persons();
			persona.setID(rs.getInt("ID"));
			persona.setTypeIdentification(rs.getString("typeIdentification"));
			persona.setIdentification(rs.getInt("identification"));
			persona.setName(rs.getString("name"));
			persona.setSurName(rs.getString("surName"));
			persona.setAddress(rs.getString("address"));
			persona.setPhone(rs.getString("phone"));
			persona.setEmail(rs.getString("email"));
			persona.setCountry(rs.getString("country"));
			
			
			pns.add(persona);
						
		}
		rs.close();
		ps.close();	
		Conexion.offConexion();
	
	return pns;
}


public List<Persons> getSelect(String dato1, String dato2) throws SQLException, Exception {
	List<Persons> pns = new ArrayList<>();
	String sql = "SELECT p.ID, p.typeIdentification, p.identification, p.name, p.surName, p.address, p.phone, p.email, p.country "
			+ "FROM persons AS p "
			+ "INNER JOIN tipopersona tp ON p.id_tipoPersona = tp.ID "
			+ "WHERE tp.tipo = '" +dato1+ "' AND p.identification='" +dato2+ "'  ORDER BY ID DESC";
		con = Conexion.getConexion("hopro","root","");
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			persona = new Persons();
			persona.setID(rs.getInt("ID"));
			persona.setTypeIdentification(rs.getString("typeIdentification"));
			persona.setIdentification(rs.getInt("identification"));
			persona.setName(rs.getString("name"));
			persona.setSurName(rs.getString("surName"));
			persona.setAddress(rs.getString("address"));
			persona.setPhone(rs.getString("phone"));
			persona.setEmail(rs.getString("email"));
			persona.setCountry(rs.getString("country"));
			
			
			pns.add(persona);
						
		}
		rs.close();
		ps.close();	
		Conexion.offConexion();
	
	return pns;
}

public Persons buscarCliente(String dato, String doc) throws SQLException, Exception {
	
	String sql = "SELECT p.ID, p.typeIdentification, p.identification, p.name,p.surName, p.address, p.phone, p.email, p.country "
			+ " FROM persons AS p "
			+ "INNER JOIN tipopersona tp ON p.id_tipoPersona = tp.ID "
			+ "WHERE tp.tipo = '" + dato + "' AND identification = '" + doc + "' ORDER BY ID DESC";
		con = Conexion.getConexion("hopro","root","");
		ps = con.prepareStatement(sql);
	
		rs = ps.executeQuery();
		while(rs.next()) {
			persona = new Persons();
			persona.setID(rs.getInt(1));
			persona.setTypeIdentification(rs.getString(2));
			persona.setIdentification(rs.getInt(3));
			persona.setName(rs.getString(4));
			persona.setSurName(rs.getString(5));
			persona.setAddress(rs.getString(6));
			persona.setPhone(rs.getString(7));
			persona.setEmail(rs.getString(8));
			persona.setCountry(rs.getString(9));			
								
		}
		rs.close();
		ps.close();	
		Conexion.offConexion();
	
	return persona;
}


public Persons validarCliente(int documentoInt, String tipo) throws SQLException, Exception {
		
	Persons persona = new Persons();	
		String sql = "SELECT p.identification, tp.tipo FROM persons AS p INNER JOIN tipopersona tp ON p.id_tipoPersona = tp.ID "
				+ "WHERE identification = '" + documentoInt+ "' AND tp.tipo = '"+ tipo +"' ";
		
		con = Conexion.getConexion("hopro","root","");
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
						
			persona.setIdentification(rs.getInt("identification"));
			persona.setTipo(new TipoPersona());
			persona.getTipo().setTipoPersona(rs.getString("tipo"));
		}
		ps.close();
			
	return persona;
}

public Persons getSelect(int ID) throws SQLException, Exception {
	Persons persona = new Persons();
	String sql = "SELECT * FROM persons WHERE ID = " + ID;
	
		con = Conexion.getConexion("hopro","root","");
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			
			persona.setID(rs.getInt(1));
			persona.setTypeIdentification(rs.getString(2));
			persona.setIdentification(rs.getInt(3));
			persona.setName(rs.getString(4));
			persona.setSurName(rs.getString(5));
			persona.setAddress(rs.getString(6));
			persona.setPhone(rs.getString(7));
			persona.setEmail(rs.getString(8));
			persona.setCountry(rs.getString(9));
			
				
					
		}
		ps.close();
	
	return persona;
}

public int getIdCliente(int doc) throws SQLException, Exception {
	int Dni = 0;
	
	String sql = "SELECT ID FROM persons WHERE identification = '"+ doc +"'";
	
	con = Conexion.getConexion("hopro","root","");
	ps = con.prepareStatement(sql);
	
	rs = ps.executeQuery();
	while(rs.next()) {
		
		Dni	= rs.getInt("ID");		
				
	}
	ps.close();
		
return Dni;

}

public void getInsert(Persons pers) throws SQLException, Exception {
		
	String sql = "INSERT INTO persons (ID, typeIdentification, identification, name, surName, address, phone, email, country, id_tipoPersona)"+
				 "VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";			
		
		
			con = Conexion.getConexion("hopro","root","");
			ps = con.prepareStatement(sql);
			ps.setString(1, pers.getTypeIdentification());
			ps.setLong(2, pers.getIdentification());
			ps.setString(3, pers.getName());
			ps.setString(4, pers.getSurName());
			ps.setString(5, pers.getAddress());
			ps.setString(6, pers.getPhone());
			ps.setString(7, pers.getEmail());
			ps.setString(8, pers.getCountry());
			ps.setInt(9, pers.getId_tipo());
			ps.executeUpdate();
			ps.close();
				
		
				
	}
	
	
	public void getUpdate(Persons pers) throws SQLException, Exception {
	String sql = "UPDATE persons SET typeIdentification = ?, identification = ?, name = ?," +
	"surName= ? , address =? , phone = ?, email= ?, country = ? , id_tipoPersona = ? WHERE ID = ?";
		
			con = Conexion.getConexion("hopro","root","");
			ps = con.prepareStatement(sql);
			ps.setString(1, pers.getTypeIdentification());
			ps.setLong(2, pers.getIdentification());
			ps.setString(3, pers.getName());
			ps.setString(4, pers.getSurName());
			ps.setString(5, pers.getAddress());
			ps.setString(6, pers.getPhone());
			ps.setString(7, pers.getEmail());
			ps.setString(8, pers.getCountry());
			ps.setInt(9, pers.getId_tipo());
			ps.setInt(10, pers.getID());
			
			ps.executeUpdate();
			
			ps.close();	
			
	}
	
public void getDelete(Persons pers) throws SQLException, Exception {
		String sql = "DELETE FROM persons WHERE ID = ?";
					con = Conexion.getConexion("hopro","root","");
			ps = con.prepareStatement(sql);
			ps.setInt(1, pers.getID());
			ps.executeUpdate();
			
			ps.close();		
		
}
	
	
		
}
