package EntregableUno;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Clase encargada de crear la base de datos y las tablas
 */
public class DB {
	private String uri; 
	private String driver;
	
	public DB() {
		this.uri = "jdbc:mysql://localhost:3306/";
		try {
			this.createDB();
			this.createTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			Connection conn = this.driverDB();
			conn.setAutoCommit(false);
			//crear la base
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public Connection driverDB() {
		try {
			return DriverManager.getConnection(this.uri, "root", "password");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//crear tablas/db
	private void createDB() throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		String sentencia = "create schema if not exists EntregableUno;";
		PreparedStatement ps = conn.prepareStatement(sentencia);
		ps.executeUpdate();
		ps.close();
		this.uri ="jdbc:mysql://localhost:3306/EntregableUno" ;
		conn.commit();
		
	}
	
	private void createTables() throws SQLException {
		Connection conn = this.driverDB();
		conn.setAutoCommit(false);
		
		//preparacion y ejecucion de sentencias
		PreparedStatement pCli = conn.prepareStatement(
		"CREATE TABLE  if not exists  Cliente(idCliente INT, nombre VARCHAR (500), email VARCHAR(500),CONSTRAINT PK_Cliente PRIMARY KEY (idCliente))");
		pCli.execute();
		pCli.close();
				
		PreparedStatement pProd = conn.prepareStatement("CREATE TABLE  if not exists  Producto(idProducto INT, nombre VARCHAR(45), valor FLOAT,CONSTRAINT PK_Producto PRIMARY KEY (idProducto))");
		pProd.execute();
		pProd.close();
				
		PreparedStatement pFac = conn.prepareStatement("CREATE TABLE  if not exists  Factura (idFactura INT, idCliente INT,CONSTRAINT PK_Factura PRIMARY KEY (idFactura), CONSTRAINT FK_Factura_Cliente FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente)");
		pFac.execute();
		pFac.close();
				
		PreparedStatement pFacProd = conn.prepareStatement("CREATE TABLE  if not exists  FacturaProducto(idFactura INT, idProducto INT, cantidad INT,"
				+ "CONSTRAINT FK_FacturaProducto_Factura FOREIGN KEY(idFactura) REFERENCES Factura(idFactura), CONSTRAINT FK_FacturaProducto_Producto FOREIGN KEY(idProducto) REFERENCES Producto(idProducto))");
		pFacProd.execute();
		pFacProd.close();
		
		conn.commit();
		
	}
	
	
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
	

}
