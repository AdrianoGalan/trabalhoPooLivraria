package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.Conexao;
import entity.Pessoa;


public class PessoaDao {
	
	private Connection c;

	public PessoaDao() throws ClassNotFoundException, SQLException {
		Conexao con = new Conexao();

		c = con.getConnection();
	}

	public int inserePessoa(Pessoa p) throws SQLException {
		
		   
        java.sql.Date sqlData = new java.sql.Date(p.getDataNascimento().getTime());

		int id = -1;

		String sql = "INSERT INTO PESSOA VALUES(?,?,?,?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, p.getNome());
		ps.setString(2, p.getCpf());
		ps.setString(3, p.getEmail());
		ps.setDate(4, sqlData);
		ps.setInt(5, p.getFkEdetecoPessoa());
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


