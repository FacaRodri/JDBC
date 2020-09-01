package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Factura extends Entidad{
	private int idFactura;
	private int idCliente;

	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Factura(int idFactura, int idCliente) {
		super();
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public int getIdCliente() {
		return idCliente;
	}

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

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", idCliente=" + idCliente + "]";
	}
	
}
