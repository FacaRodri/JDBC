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

		//MySql
		String driver = "com.mysql.cj.jdbc.Driver";
		String uri = "jdbc.msql://localhost:3306/EntregableUno"; //el entregable uno hay que crearlo en nuestro workbrench local
		
		
		String sentencia = "CREATE TABLE Cliente(" + 
				"id INT, nombre VARCHAR(500), email VARCHAR(500), PRIMARY KEY(id))";
		
		Entidad entidad = new Entidad();
		Cliente cliente = new Cliente();
		
		CSVParser parser;
		try {
			List<Producto> productos=new LinkedList<Producto>();
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("data/productos.csv"));
			int index = 0;
			for(CSVRecord row: parser) {
				productos.add(new Producto(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor"))));
				System.out.println(productos.get(index));
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
