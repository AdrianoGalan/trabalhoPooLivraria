package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de conexão com o Banco.sql.
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class Conexao {
	
	/** Conexao c. */
	private Connection c;
	
	/**
	 * Método que retorna a conexao com o banco
	 * @return a conexao
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		String hostName = "192.168.99.100";
		String dbName = "LIVRARIA";
		String user = "sa";
		String senha = "Roberto@123";
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");

		c = DriverManager.getConnection(String.format(
				"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", hostName, dbName, user, senha));

		return c;
		
	}
	
	/**
	 * Método que fecha a conexão com o Banco.sql.
	 * @throws SQLException
	 */
	
	public void closeConnection() throws SQLException {
		c.close();
	}
	

}
