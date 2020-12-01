package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;


/**
 * Classe Dao do Relatorio
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class RelatorioDao {

	/** Conexao c. */
	private Connection c;

	/**
	 * Classe que recupera a conexão com o Banco.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public RelatorioDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();

		c = con.getConnection();
	}

	/**
	 * Método que busca os livros mais vendidos.
	 * 
	 * @return lista de livros
	 * @throws SQLException
	 */
	public ObservableList<ModelTabelaLivro> livroMaisVendido() throws SQLException {

		ModelTabelaLivro l;

		ObservableList<ModelTabelaLivro> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT l.ISBN, l.TITULO , COUNT(FK_LIVRO_ITENSVENDA) AS QTD ");
		sql.append("FROM ITENS_VENDA iv INNER JOIN LIVRO l  ");
		sql.append("ON l.ID_LIVRO = iv.FK_LIVRO_ITENSVENDA  ");
		sql.append("GROUP BY l.TITULO, l.ISBN  ");
		sql.append("ORDER BY QTD DESC");

		PreparedStatement ps = c.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			l = new ModelTabelaLivro();

			l.setIsbn(rs.getString("ISBN"));
			l.setTitulo(rs.getString("TITULO"));
			l.setQdtVenda(rs.getInt("QTD"));
			lista.add(l);

		}
		rs.close();
		ps.close();

		return lista;
	}

	/**
	 * Método que busca os livros menos vendidos.
	 * 
	 * @return lista de livros
	 * @throws SQLException
	 */
	public ObservableList<ModelTabelaLivro> livroMenosVendido() throws SQLException {

		ModelTabelaLivro l;

		ObservableList<ModelTabelaLivro> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT l.ISBN, l.TITULO , COUNT(FK_LIVRO_ITENSVENDA) AS QTD ");
		sql.append("FROM ITENS_VENDA iv INNER JOIN LIVRO l  ");
		sql.append("ON l.ID_LIVRO = iv.FK_LIVRO_ITENSVENDA  ");
		sql.append("GROUP BY l.TITULO, l.ISBN ");
		sql.append("ORDER BY QTD ");

		PreparedStatement ps = c.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			l = new ModelTabelaLivro();

			l.setIsbn(rs.getString("ISBN"));
			l.setTitulo(rs.getString("TITULO"));
			l.setQdtVenda(rs.getInt("QTD"));
			lista.add(l);

		}
		rs.close();
		ps.close();

		return lista;
	}

	/**
	 * Método que busca livros com estoque inferior a 5.
	 * 
	 * @return lista de livros
	 * @throws SQLException
	 */
	public ObservableList<ModelTabelaLivro> livroEstoqueBaixo() throws SQLException {

		ModelTabelaLivro l;

		ObservableList<ModelTabelaLivro> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT l.ISBN, l.TITULO, l.QTS_ESTOQUE  ");
		sql.append("FROM LIVRO l  ");
		sql.append("WHERE l.QTS_ESTOQUE < 5 ");

		PreparedStatement ps = c.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			l = new ModelTabelaLivro();

			l.setIsbn(rs.getString("ISBN"));
			l.setTitulo(rs.getString("TITULO"));
			l.setQtsEstoque(rs.getInt("QTS_ESTOQUE"));
			lista.add(l);

		}
		rs.close();
		ps.close();

		return lista;
	}

	/**
	 * Método que busca data e os ultimos livros vendidos.
	 * 
	 * @return lista
	 * @throws SQLException
	 */
	public ObservableList<ModelTabelaLivro> dataVenda() throws SQLException {

		ModelTabelaLivro l;

		ObservableList<ModelTabelaLivro> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT l.ISBN , l.TITULO , MAX( CONVERT(CHAR(10), v.DATA_VENDA , 103)) AS DAtAV ");
		sql.append("FROM LIVRO l INNER JOIN ITENS_VENDA iv ");
		sql.append("ON l.ID_LIVRO = iv.FK_LIVRO_ITENSVENDA  ");
		sql.append("INNER JOIN VENDA v  ");
		sql.append("ON v.ID_VENDA = iv.FK_VENDA_ITENSVENDA ");
		sql.append("GROUP BY l.ISBN , l.TITULO ");

		PreparedStatement ps = c.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			l = new ModelTabelaLivro();

			l.setIsbn(rs.getString("ISBN"));
			l.setTitulo(rs.getString("TITULO"));
			l.setDataUltimaVenda(rs.getString("DAtAV"));
			lista.add(l);

		}
		rs.close();
		ps.close();

		return lista;
	}

	/**
	 * Método que mostra o melhor dia da semana para venda.
	 * 
	 * @return dia da semana
	 * @throws SQLException
	 */
	public int melhorDiaSemana() throws SQLException {
		
		int dia = -1;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DATEPART(dw, v.DATA_VENDA) AS DIA , COUNT(iv.FK_VENDA_ITENSVENDA) AS TOTAL_ITENS ");
		sql.append("FROM VENDA v INNER JOIN ITENS_VENDA iv  ");
		sql.append("ON v.ID_VENDA = iv.FK_VENDA_ITENSVENDA  ");
		sql.append("GROUP BY DATEPART(dw, v.DATA_VENDA) ");
		sql.append("ORDER BY TOTAL_ITENS ");

		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			
			dia = rs.getInt("DIA");
			
		}
		rs.close();
		ps.close();
		
		return dia;
	}

}
