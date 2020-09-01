package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente extends Entidad{
	private int idCliente;
	private String nombre;
	private String email;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(int idCliente, String nombre, String email) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
	}

	//getter && setter
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public int getId() {return idCliente;}
	
	
	@Override
	public String toString() {
		return "Cliente [id=" + idCliente + ", nombre=" + nombre + ", email=" + email + "]";
	}
	
	public void addCliente(String name, String email) throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String insert = "INSERT INTO Cliente(id, nombre, email) VALUES(?,?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idCliente);
		ps.setString(2, name);
		ps.setString(3, email);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
	
	
}
