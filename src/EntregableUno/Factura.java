package EntregableUno;

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


	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", idCliente=" + idCliente + "]";
	}
	
}
