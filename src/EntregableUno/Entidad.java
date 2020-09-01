package EntregableUno;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * Descripcion basica, que hace la clase? que provee
 * @author Alan
 * 
 */
public class Entidad {
	protected DB db;

	public Entidad() {
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
