package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connection.Conexao;
import javafx.collections.ObservableList;
import tabelaModel.ModelItensVenda;

/**
 * Classe Dao de itens venda
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ItensVendaDao {

	/** Conexao c. */
	private Connection c;

	/**
	 * Classe que recupera a conexao com o Banco.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ItensVendaDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();
	}

	/**
	 * Metodo insere um livro na tabela ITENS_VENDA do Banco de dados
	 * 
	 * @param idVenda
	 * @param itensVenda
	 * @throws SQLException
	 */
	public void insereLivro(int idVenda, ObservableList<ModelItensVenda> itensVenda) throws SQLException {

		String sql = "INSERT INTO ITENS_VENDA VALUES (" + idVenda + ", ?)";

		PreparedStatement ps = c.prepareStatement(sql);

		for (int i = 0; i < itensVenda.size(); i++) {
			for (int j = 0; j < itensVenda.get(i).getQtsVenda(); j++) {

				ps.setInt(1, itensVenda.get(i).getIdLivro());
				ps.executeUpdate();
			}
		}
		ps.close();

	}

}
