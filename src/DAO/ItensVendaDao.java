package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.Conexao;
import entity.Livro;
import javafx.collections.ObservableList;
import tabelaModel.ModelItensVenda;

public class ItensVendaDao {

	private Connection c;

	public ItensVendaDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();
	}

	public void insereLivro(int idVenda, ObservableList<ModelItensVenda> itensVenda) throws SQLException {

		String sql = "INSERT INTO ITENS_VENDA VALUES (" + idVenda + ", ?)";

		PreparedStatement ps = c.prepareStatement(sql);

		for (int i = 0; i < itensVenda.size(); i++) {

			
			ps.setInt(1, itensVenda.get(i).getIdLivro());
			ps.executeUpdate();

		}
		ps.close();

	}

}
