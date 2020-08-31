package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Producto extends Entidad{
	
	public void addProducto(int idProducto, String nombre, float valor) throws SQLException {
		Connection conn = this.driverDB();
		String insert = "INSERT INTO Producto(idProducto, nombre, valor) VALUES(?,?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idProducto);
		ps.setString(2, nombre);
		ps.setFloat(3, valor);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
}
