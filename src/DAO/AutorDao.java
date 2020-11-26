package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.Conexao;
import entity.Autor;

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

}
