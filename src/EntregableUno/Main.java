package EntregableUno;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		Entidad entidad = new Entidad();
		Cliente cliente = new Cliente();
		try {
			cliente.addCliente("pepe", "jaja@hotmail.com");
			cliente.addCliente("pepe", "jaja@hotmail.com");
			cliente.addCliente("pepe", "jaja@hotmail.com");
			cliente.addCliente("pepe", "jaja@hotmail.com");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
