package EntregableUno;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Clase encargada de crear la base de datos y las tablas
 */
public class DB {
	private String uri; 
	private String driver;
	
	public DB() {
		this.uri = "jdbc:mysql://localhost:3306/";
		try {
			this.createDB();
			//inicializar tablas
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			Connection conn = this.driverDB();
			conn.setAutoCommit(false);
			//crear la base
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public Connection driverDB() {
		try {
			return DriverManager.getConnection(this.uri, "root", "password");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//crear tablas/db
	public void createDB() throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String sentencia = "create schema if not exists EntregableUno;";
		PreparedStatement ps = conn.prepareStatement(sentencia);
		ps.executeUpdate();
		ps.close();
		this.uri ="jdbc:mysql://localhost:3306/EntregableUno" ;
		conn.commit();
		
	}
	
	
	

}
