package EntregableUno;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Descripcion basica, que hace la clase? que provee
 * @author Alan
 * 
 */
public class Entidad {
	protected DB db;

	public Entidad() {
		this.db = new DB();
	}
	
	public Connection driverDB() {
		return this.db.driverDB();
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
