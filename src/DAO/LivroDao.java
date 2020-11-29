package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Livro;
import entity.Preco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;

public class LivroDao {

	private Connection c;

	public LivroDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();
	}

	public void insereLivro(Livro livro) throws SQLException {

		String sql = "INSERT INTO LIVRO VALUES ( ?, ?)";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, livro.getIdLivro());

		ps.executeUpdate();

		ps.close();

	}

	public ObservableList<ModelTabelaLivro> buscaLivro(String texto, int meio) throws SQLException {

		ModelTabelaLivro l;

		ObservableList<ModelTabelaLivro> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT l.ID_LIVRO, p.VALOR AS PRECO , l.TITULO, a.NOME AS AUTOR , l.ISBN,l.GENERO, l.EDICAO, l.ANO,l.QTS_ESTOQUE,l.IDIOMA, l.DESCRICAO ");
		sql.append("FROM LIVRO l INNER JOIN LIVRO_AUTOR la ");
		sql.append("ON l.ID_LIVRO = la.FK_LIVRO_LIVRO_AUTOR ");
		sql.append("INNER JOIN AUTOR a ");
		sql.append("ON a.ID_AUTOR = la.FK_AUTOR_LIVRO_AUTOR ");
		sql.append("INNER JOIN PRECO p ");
		sql.append("ON p.ID_PRECO = l.PRECO_ATUAL  ");

		if (meio == 0) {
			sql.append("WHERE l.TITULO LIKE  ?");
		} else if (meio == 1) {
			sql.append(" WHERE l.ISBN LIKE ? ");
		} else if (meio == 2) {
			sql.append("WHERE a.NOME LIKE ?");
		}

		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, "%" + texto + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			l = new ModelTabelaLivro();

			l.setIdLivro(rs.getInt("ID_LIVRO"));
			l.setPreco("R$ " + rs.getDouble("PRECO"));
			l.setTitulo(rs.getString("TITULO"));
			l.setAutor(rs.getString("AUTOR"));
			l.setIsbn(rs.getString("ISBN"));
			l.setGenero(rs.getString("GENERO"));
			l.setEdicao(rs.getString("EDICAO"));
			l.setAno(rs.getString("ANO"));
			l.setQtsEstoque(rs.getInt("QTS_ESTOQUE"));
			l.setIdioma(rs.getString("IDIOMA"));
			l.setDescricao(rs.getString("DESCRICAO"));
			lista.add(l);

		}
		rs.close();
		ps.close();

		return lista;
	}

	public void atualizaEstoque(int idLivro, int qtsEstoque) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE LIVRO ");
		sql.append("SET QTS_ESTOQUE = ? ");
		sql.append("WHERE ID_LIVRO = ? ");

		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, qtsEstoque);
		ps.setInt(2, idLivro);

		ps.executeUpdate();

		ps.close();

	}

	public ObservableList<Livro> buscaLivroTitulo(String titulo) throws SQLException {

		Livro l;
		ObservableList<Livro> lista = FXCollections.observableArrayList();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT l.ID_LIVRO , l.TITULO , l.ISBN , l.GENERO , l.EDICAO , l.ANO , ");
		sql.append("l.PRECO_ATUAL, l.QTS_ESTOQUE , l.IDIOMA , l.DESCRICAO ");
		sql.append("FROM LIVRO l ");
		sql.append("WHERE l.TITULO LIKE ? ");

		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, "%" + titulo + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			l = new Livro();

			l.setIdLivro(rs.getInt("ID_LIVRO"));
			l.setTitulo(rs.getString("TITULO"));
			l.setIsbn(rs.getString("ISBN"));
			l.setGenero(rs.getString("GENERO"));
			l.setEdicao(rs.getString("EDICAO"));
			l.setAno(rs.getString("ANO"));
			l.setQtsEstoque(rs.getInt("QTS_ESTOQUE"));
			l.setPrecoAtual(rs.getInt("PRECO_ATUAL"));
			l.setIdioma(rs.getString("IDIOMA"));
			l.setDescricao(rs.getString("DESCRICAO"));
			lista.add(l);

		}
		rs.close();
		ps.close();

		return lista;
	}

	public boolean verificaDuplicIsbn(String isbn) throws SQLException {
		
		boolean u = false;
		String sql = "select ISBN from LIVRO where ISBN = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, isbn);

		ResultSet rs = ps.executeQuery();

		while (rs.next() && u == false) {
			if (rs.getString("ISBN").equals(isbn)) {
				u = true;
			}
		}

		rs.close();
		ps.close();

		return u;

	}

	public void alteraPrecoLivro(Preco p) throws SQLException {
		String sql = "update LIVRO set Preco_Atual = ? where ID_LIVRO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setDouble(1, p.getIdPreco());
		ps.setInt(2, p.getFkLivroPreco());

		ps.execute();
		ps.close();

	}

}
