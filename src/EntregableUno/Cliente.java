package EntregableUno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente extends Entidad{
	private int id;
	private static int idEstatico = 0;
	private String nombre;
	private String email;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	//getter && setter
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public int getId() {return id;}
	
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
	}
	
	public void addCliente(String name, String email) throws SQLException {
		this.idEstatico = idEstatico + 1;
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String insert = "INSERT INTO Cliente(id, nombre, email) VALUES(?,?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idEstatico);
		ps.setString(2, name);
		ps.setString(3, email);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
	
	
}
