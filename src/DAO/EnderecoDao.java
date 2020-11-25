package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.Conexao;
import entity.Endereco;

public class EnderecoDao {
	
	private Connection c;
	
	public EnderecoDao()throws ClassNotFoundException, SQLException{
		Conexao con = new Conexao();
		
		c = con.getConnection();
	}

	public int insereEndereco(Endereco e) throws SQLException{
		String sql = "INSERT INTO ENDERECO VALUES(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, e.getRua());
		ps.setInt(2, e.getNumero());
		ps.setString(3, e.getBairro());
		
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
		    int lastId = rs.getInt(1);
		}

		ps.execute();
		ps.close();
		
		return 0;
	}
	
	
}
