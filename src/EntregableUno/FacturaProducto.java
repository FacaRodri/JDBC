package EntregableUno;


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

	

	@Override
	public String toString() {
		return "FacturaProducto [idProducto=" + idProducto + ", idFactura=" + idFactura + ", cantidad=" + cantidad
				+ "]";
	}
	
}
