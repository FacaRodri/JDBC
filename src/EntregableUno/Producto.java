package EntregableUno;

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


	
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + " valor=" + valor + "]";
	}
	
}
