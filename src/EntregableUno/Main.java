package EntregableUno;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.Line;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

	public static void main(String[] args) {
		
		/*
			//MySql
			String driver = "com.mysql.cj.jdbc.Driver";
			String uri = "jdbc.msql://localhost:3306/EntregableUno"; //el entregable uno hay que crearlo en nuestro workbrench local
			
			
			String sentencia = "CREATE TABLE Cliente(" + 
					"id INT, nombre VARCHAR(500), email VARCHAR(500), PRIMARY KEY(id))";
			
			Entidad entidad = new Entidad();
			Cliente cliente = new Cliente();
		*/
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
			
			int index = 0;//es el index con el que se muestran los datos que se parsean en cada iteracion este vuelve a 0 al finalizar el for
			
		//inicializado de listas segun parsers
			
			//productos
			for(CSVRecord row: productosParser) {
				productos.add(new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor"))));
				//System.out.println(productos.get(index));
				index++;
			}index = 0; 
			
			//clientes
			for(CSVRecord row: clientesParser) {
				clientes.add(new Cliente(Integer.parseInt(row.get("idCliente")),row.get("nombre"),row.get("email")));
				//System.out.println(clientes.get(index));
				index++;
			}index = 0; 
			
			//facturas
			for(CSVRecord row: facturasParser) {
				facturas.add(new Factura(Integer.parseInt(row.get("idCliente")),Integer.parseInt(row.get("idFactura"))));
				//System.out.println(facturas.get(index));
				index++;
			}index = 0; 
			
			//facturaProducto
			for(CSVRecord row: facturasProductoParser) {
				facturaProducto.add(new FacturaProducto(Integer.parseInt(row.get("idProducto")),Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("cantidad"))));
				System.out.println(facturaProducto.get(index));
				index++;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		
	}

}
