package EntregableUno;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase DataBase
 * 
 * Contiene los metodos necesarios las consultas necesarias en la base de datos
 * 
 * @author Grupo6
 * 
 * @version 1.0
 * 
 */


public class DB {
	
	//Atributos
	/**
	 * Uniform Resource Identifier
	 */
	private String uri; 
	
	/**
	 * Constructor por defecto
	 */
	public DB() {
		this.uri = "jdbc:mysql://localhost:3306/";
	}
	
	//Metodos publicos
	
	/**
	 * Crea una instancia del driver y se conecta a la base de datos
	 * Una vez conectada crea el esquema necesario para crear las tablas
	 * Por ultimo crea las tablas necesarias y cierra la coneccion
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			Connection conn = this.driverDB();
			conn.setAutoCommit(false);
			this.createDB(conn);
			this.createTables(conn);
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve el la coneccion a la base
	 * @return Coneccion a la base de datos
	 */
	public Connection driverDB() {
		try {
			return DriverManager.getConnection(this.uri, "root", "password");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Se conecta a la base de datos y agrega un producto a la misma
	 * @param idProducto	identificador del producto		
	 * @param nombre		nombre del producto
	 * @param valor			valor del producto
	 * @throws SQLException
	 */
	public void addProducto(int idProducto, String nombre, float valor) throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String insert = "INSERT INTO Producto(idProducto, nombre, valor) VALUES(?,?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idProducto);
		ps.setString(2, nombre);
		ps.setFloat(3, valor);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
	
	/**
	 * Se conecta a la base de datos y agrega una relacion Factura-Producto 
	 * @param idProducto	identificador del producto
	 * @param idFactura		identificador de la factura
	 * @param cantidad		cantidad de productos vendidos
	 * @throws SQLException
	 */
	public void addFacturaProducto( int idProducto, int idFactura, int cantidad) throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String insert = "INSERT INTO FacturaProducto(idProducto, idFactura, cantidad) VALUES(?,?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idProducto);
		ps.setInt(2, idFactura);
		ps.setInt(3, cantidad);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
	
	/**
	 * Se conecta a la base de datos y agrega una factua a la misma
	 * @param idFactura		identificador de la factura
	 * @param idCliente		identificador del cliente
	 * @throws SQLException
	 */
	public void addFactura(int idFactura, int idCliente) throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String insert = "INSERT INTO Factura(idFactura,idCliente) VALUES(?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idFactura);
		ps.setInt(2, idCliente);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
	
	/**
	 * Se conecta a la base de datos y agrega un cliente a la misma
	 * @param idCliente		identificador del cliente
	 * @param nombre		nombre del cliente
	 * @param email			email del cliente
	 * @throws SQLException
	 */
	public void addCliente(int idCliente, String nombre, String email) throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String insert = "INSERT INTO Cliente(idCliente, nombre, email) VALUES(?,?,?)"; //
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idCliente);
		ps.setString(2, nombre);
		ps.setString(3, email);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}
	
	/**
	 * Se conecta a la base de datos ejecuta una sentencia sql
	 * @return los datos necesarios para resolver "el producto que mas recaudo"
	 * @throws SQLException
	 */
	public String productoMasRecaudo() throws SQLException {
		String sentencia = "SELECT p.idProducto, valor, count(*) cantidad, valor*count(*) resultado, p.nombre " + 
				" FROM EntregableUno.Producto p, EntregableUno.FacturaProducto fp" + 
				" where p.idProducto = fp.idProducto" + 
				" group by idProducto" + 
				" order by resultado desc" + 
				" limit 1;";
		Connection conn = this.driverDB();
		PreparedStatement ps = conn.prepareStatement(sentencia);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			sentencia = "idProducto: "+ rs.getInt(1) + " total: "+rs.getInt(4)+ " nombre: "+rs.getString(5);
		}
		return sentencia;
	}
	
	/**
	 * Se conecta a la base de datos y ejecuta una sentencia sql
	 * @return Lista ordenada de clientes
	 * @throws SQLException
	 */
	public Iterator<String> listaClientes() throws SQLException {
		String sentencia = "SELECT c.nombre, t2.facturacion" + 
				" FROM EntregableUno.Cliente c," + 
				" (SELECT f.idCliente, SUM(t1.valor) facturacion" + 
				" FROM EntregableUno.Factura f," + 
				" (SELECT fp.idFactura, SUM(fp.cantidad*p.valor) valor" + 
				" FROM EntregableUno.FacturaProducto fp, EntregableUno.Producto p" + 
				" WHERE fp.idProducto=p.idProducto" + 
				" GROUP BY fp.idFactura) t1" + 
				" WHERE f.idFactura=t1.idFactura" + 
				" GROUP BY f.idCliente) t2" + 
				" WHERE c.idCliente=t2.idCliente" + 
				" ORDER BY facturacion DESC;";
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		PreparedStatement ps = conn.prepareStatement(sentencia);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> clientes = new ArrayList<String>();
		while (rs.next()) {
			clientes.add("nombre: "+ rs.getString(1) + ", facturacion: "+rs.getInt(2));
		}
		return clientes.iterator();
	}
	
	/**
	 * Metodo encargado de crear el esquema en la base de datos
	 * una vez creada actualiza el URI segun el nombre del esquema
	 * @param conn coneccion a la base de datos
	 * @throws SQLException
	 */
	private void createDB(Connection conn) throws SQLException {
		String sentencia = "CREATE SCHEMA EntregableUno;";
		PreparedStatement ps = conn.prepareStatement(sentencia);
		ps.executeUpdate();
		ps.close();
		this.uri ="jdbc:mysql://localhost:3306/EntregableUno" ;
		conn.commit();
		
	}
	
	/**
	 * Crea las tablas y sus relaciones
	 * @param conn coneccion a la base de datos
	 * @throws SQLException
	 */
	private void createTables(Connection conn) throws SQLException {
		
		//preparacion y ejecucion de sentencias
		PreparedStatement pCli = conn.prepareStatement(
		"CREATE TABLE  if not exists  EntregableUno.Cliente(idCliente INT, nombre VARCHAR (500), email VARCHAR(500), PRIMARY KEY (idCliente))");
		pCli.execute();
		pCli.close();
				
		PreparedStatement pProd = conn.prepareStatement("CREATE TABLE  if not exists  EntregableUno.Producto(idProducto INT, nombre VARCHAR(45), valor FLOAT, PRIMARY KEY (idProducto))");
		pProd.execute();
		pProd.close();
				
		PreparedStatement pFac = conn.prepareStatement("CREATE TABLE  if not exists  EntregableUno.Factura (idFactura INT, idCliente INT, PRIMARY KEY (idFactura), INDEX (idCliente), FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente))");
		pFac.execute();
		pFac.close();
				
		PreparedStatement pFacProd = conn.prepareStatement("CREATE TABLE  if not exists  EntregableUno.FacturaProducto(idFactura INT, idProducto INT, cantidad INT,"
				+ " FOREIGN KEY(idFactura) REFERENCES Factura(idFactura), FOREIGN KEY(idProducto) REFERENCES Producto(idProducto))");
		pFacProd.execute();
		pFacProd.close();
		
		conn.commit();
		
	}
	
}
