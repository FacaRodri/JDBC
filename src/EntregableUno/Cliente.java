package EntregableUno;

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
	
	
	
}
