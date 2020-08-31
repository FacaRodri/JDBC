package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Factura extends Entidad{


	public void addFactura(int idFactura, int idCliente) throws SQLException {
		Connection conn = this.driverDB();
		String insert = "INSERT INTO Factura(idFactura,idCliente) VALUES(?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idCliente);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
}
