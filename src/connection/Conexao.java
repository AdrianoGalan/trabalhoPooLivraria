package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		String hostName = "localhost";
		String dbName = "LIVRARIA";
		String user = "sa";
		String senha = "P4ssw0rd";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");

		c = DriverManager.getConnection(String.format(
				"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", hostName, dbName, user, senha));

		return c;
		
	}
	
	public void closeConnection() throws SQLException {
		c.close();
	}
	

}
