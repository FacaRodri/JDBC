package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Producto extends Entidad{
	private int idProducto;
	private String nombre;
	private float valor;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getIdProducto() {
		return idProducto;
	}

	public Producto(int idProducto, String nombre, float valor) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.valor = valor;
	}
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}


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
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + " valor=" + valor + "]";
	}
	
}
