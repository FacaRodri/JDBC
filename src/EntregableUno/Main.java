package EntregableUno;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

	public static void main(String[] args) throws SQLException {
		BufferedReader entrada = new BufferedReader( new InputStreamReader(System.in));
		@SuppressWarnings("deprecation")
		Integer dato = 0;
		
		try {
			System.out.println("Para crear y cargar la base de datos press 1");
			System.out.println("Para ver los datos(teniendo la base cargada) press 2");
			dato = new Integer(entrada.readLine());
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DB db = new DB();
		
		if (dato == 1 ) {
			db.connect();
			Entidad entidad = new Entidad();
			Cliente cliente = new Cliente();
			
			CSVParser productosParser;
			CSVParser clientesParser;
			CSVParser facturasParser;
			CSVParser facturasProductoParser;
			
			try {
				//listas
				List<Producto> productos=new LinkedList<Producto>();
				List<Cliente> clientes=new LinkedList<Cliente>();
				List<Factura> facturas=new LinkedList<Factura>();
				List<FacturaProducto> facturaProducto=new LinkedList<FacturaProducto>();
				
				//parsers
				productosParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("data/productos.csv"));
				clientesParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("data/clientes.csv"));
				facturasParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("data/facturas.csv"));
				facturasProductoParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("data/facturas-productos.csv"));
				
			//inicializado de listas segun parsers
				//clientes
				for(CSVRecord row: clientesParser) {
					Cliente c = new Cliente(Integer.parseInt(row.get("idCliente")),row.get("nombre"),row.get("email"));
					db.addCliente(c.getId(), c.getNombre(), c.getEmail());
				}
				//facturas
				for(CSVRecord row: facturasParser) {
					Factura f = new Factura(Integer.parseInt(row.get("idFactura")),Integer.parseInt(row.get("idCliente")));
					db.addFactura(f.getIdFactura(), f.getIdCliente());
				}
				//productos
				for(CSVRecord row: productosParser) {
					Producto p = new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor")));
					db.addProducto(p.getIdProducto(), p.getNombre(), p.getValor());
				}
				//facturaProducto
				for(CSVRecord row: facturasProductoParser) {
					FacturaProducto fp = new FacturaProducto(Integer.parseInt(row.get("idProducto")),Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("cantidad")));
					db.addFacturaProducto(fp.getIdProducto(), fp.getIdFactura(), fp.getCantidad());
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			db.select();
		}
		
	}

}
