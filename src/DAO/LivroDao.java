package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;

import entity.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LivroDao {
	private Connection c;
	public LivroDao() throws ClassNotFoundException, SQLException {
		Conexao con = new Conexao();
		c = con.getConnection();
	}
	
	public ObservableList<Livro> buscaLivro(String texto,String meio) throws SQLException{
		Livro l;
		ObservableList<Livro> lista = FXCollections.observableArrayList();
		String sql;
		if(meio.equals("a.NOME")) {
			sql = " Select l.ID_LIVRO,l.TITULO,l.ISBN,l.GENERO,l.EDICAO,l.ANO,l.QTS_ESTOQUE,l.IDIOMA,l.DESCRICAO from LIVRO l, LIVRO_AUTOR la, AUTOR a " + 
				  " where l.ID_LIVRO = la.FK_LIVRO_LIVRO_AUTOR and a.ID_AUTOR = la.FK_AUTOR_LIVRO_AUTOR  " +
				  " and a.NOME Like ?";
		}else {
			sql = " Select * from LIVRO  where " + meio + " Like ? ";
		}
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, texto+"%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			l = new Livro();
			l.setIdLivro(rs.getInt("ID_LIVRO"));
			l.setTitulo(rs.getString("TITULO"));
			l.setIsbn(rs.getString("ISBN"));
			l.setGenero(rs.getString("GENERO"));
			l.setEdicao(rs.getString("EDICAO"));
			l.setAno(rs.getString("ANO"));
			l.setQtsEstoque(rs.getInt("QTS_ESTOQUE"));
			l.setIdioma(rs.getString("IDIOMA"));
			l.setPreco(rs.getDouble("Preco_Atual"));
			l.setDescricao(rs.getString("DESCRICAO"));
			lista.add(l);
			
		}
		rs.close();
		ps.close();
		
		return lista;
	}
	
}
