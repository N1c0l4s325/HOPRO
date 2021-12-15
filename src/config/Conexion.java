package config;

import java.sql.*;

public class Conexion {

	static String Errores = null;
	static Connection  cnx = null;
	 
	public Conexion() {

	}

	public static Connection getConexion(String dataBase, String user, String pword) {

try {
			
			Class.forName("com.mysql.jdbc.Driver");

			cnx = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBase+"?zeroDateTimeBehavior=convertToNull", user, pword);
			
			

} catch (ClassNotFoundException ex) {

			System.out.println("Driver no encontrado " + ex.getMessage());

} catch (SQLException ex) {
			System.out.println("SQLException, error en la conexion a la base de datos" + ex.getMessage());
}
		return cnx;

}

	public static void offConexion() {

try {
			cnx.close();
} catch (SQLException e) {
			System.out.println("Error al desconectar base de datos" + e.getMessage());

} catch (Exception e) {
			System.out.println("ERROR desconocdo" + e.getMessage()); 
		}

	}
	
	public static void main(String[] args) {
		System.out.println(Conexion.getConexion("hopro", "root", ""));
	}
	
	
	
}
