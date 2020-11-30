package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Conexao;
import entity.Preco;
import javafx.collections.ObservableList;
import tabelaModel.ModelItensVenda;

/**
 * Classe Dao da Venda
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class VendaDao {
	
	/** Conexao c. */
	private Connection c;

	/**
	 * Classe que recupera a conexão com o Banco.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public VendaDao() throws ClassNotFoundException, SQLException {
		
		Conexao con = new Conexao();

		c = con.getConnection();
	}
	
	/**
	 * Método que insere dados na tabela venda do Banco de dados.
	 * @param idCliente
	 * @return id
	 * @throws SQLException
	 */
	public int inserirVenda(int idCliente) throws SQLException {
		
		int id = -1;

		String sql = "INSERT INTO VENDA VALUES( GETDATE(), ?)";
		
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.setDouble(1, idCliente);
		
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			id = rs.getInt(1);
		}

		rs.close();
		ps.close();

		return id;
		
	}


}
