package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AutorDao {
	private Connection c;
	
	public AutorDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();
	}


	public void insereAutor(Autor autor) throws SQLException {
		
		String sql = "INSERT INTO AUTOR VALUES (?,?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, autor.getIdAutor());
		ps.setString(2, autor.getNome());
		ps.setString(3, autor.getNacionalidade());

		ps.executeUpdate();

		ps.execute();

		ps.close();
		
		
	}
	
	public ObservableList<Autor> listarAutores() throws SQLException{
		ObservableList<Autor> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from AUTOR");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Autor a = new Autor();
			a.setIdAutor(rs.getInt("ID_AUTOR"));
			a.setNome(rs.getString("NOME"));
			a.setNacionalidade(rs.getString("NACIONALIDADE"));
			lista.add(a);
		}
		
		return lista;
	}

}
