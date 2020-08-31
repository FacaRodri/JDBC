package EntregableUno;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Entidad {

	private String uri; 
	
	public String getUri() {return uri;}
	public void setUri(String uri) {this.uri = uri;}

	public Entidad(String uri) {
		super();
		this.uri = uri;
	}
	public Entidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public  void connect(String driver) {
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
	
	public  void createTable(Connection conn, String sentencia) {
		String table = sentencia;
		try {
			conn.prepareStatement(table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
