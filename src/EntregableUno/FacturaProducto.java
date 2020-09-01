package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaProducto extends Entidad{
	private int idProducto;
	private int idFactura;
	private int cantidad;
	
	
	public FacturaProducto(int idProducto, int idFactura, int cantidad) {
		super();
		this.idProducto = idProducto;
		this.idFactura = idFactura;
		this.cantidad = cantidad;
	}

	public FacturaProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public int getIdFactura() {
		return idFactura;
	}

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

	@Override
	public String toString() {
		return "FacturaProducto [idProducto=" + idProducto + ", idFactura=" + idFactura + ", cantidad=" + cantidad
				+ "]";
	}
	
}
