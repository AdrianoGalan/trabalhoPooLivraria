package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;

import entity.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;

public class LivroDao {
	private Connection c;
	public LivroDao() throws ClassNotFoundException, SQLException {
		
		Conexao con = new Conexao();
		c = con.getConnection();
	}
	
	public ObservableList<ModelTabelaLivro> buscaLivro(String texto,int meio) throws SQLException{
		
		ModelTabelaLivro l;
		
		ObservableList<ModelTabelaLivro> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.VALOR AS PRECO , l.TITULO, a.NOME AS AUTOR , l.ISBN,l.GENERO, l.EDICAO, l.ANO,l.QTS_ESTOQUE,l.IDIOMA, l.DESCRICAO ");
		sql.append("FROM LIVRO l INNER JOIN LIVRO_AUTOR la ");
		sql.append("ON l.ID_LIVRO = la.FK_LIVRO_LIVRO_AUTOR ");
		sql.append("INNER JOIN AUTOR a ");
		sql.append("ON a.ID_AUTOR = la.FK_AUTOR_LIVRO_AUTOR ");
		sql.append("INNER JOIN PRECO p ");
		sql.append("ON p.FK_LIVRO_PRECO = l.ID_LIVRO ");
		
		
		
		
		if(meio == 0) {
			sql.append("WHERE l.TITULO LIKE  ?");
		}else if(meio == 1) {
			sql.append(" WHERE l.ISBN LIKE ? ");
		}else if(meio == 2) {
			sql.append("WHERE a.NOME LIKE ?");
		}
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, "%"+texto+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			
			l = new ModelTabelaLivro();
			
			l.setPreco("R$ " +rs.getDouble("PRECO"));
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
	
}
