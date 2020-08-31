package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaProducto extends Entidad{
	
	
	public void addProducto( int idProducto, int idFactura, int cantidad) throws SQLException {
		Connection conn = this.driverDB();
		String insert = "INSERT INTO FacturaProducto(idProducto, idFactura, cantidad) VALUES(?,?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idProducto);
		ps.setInt(2, idFactura);
		ps.setInt(3, cantidad);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
}
