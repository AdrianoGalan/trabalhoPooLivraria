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
	
	public int buscaPessoa(String CPF) throws SQLException {
		int id = -1;
		String sql = "select ID_PESSOA,CPF from Pessoa where CPF = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, CPF);
		ResultSet rs = ps.executeQuery();
		
		if(rs.getString("CPF").equals(CPF)) {
			id = rs.getInt("ID_PESSOA");
		}

		rs.close();
		ps.close();
		return id;
	}
	
	public void atualizaPessoa(Pessoa p) throws SQLException {
		

		String sql = "update PESSOA set NOME = ?,CPF = ?, EMAIL = ?, DATA_NASCIMENTO = ? where ID_PESSOA = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getNome());
		ps.setString(2, p.getCpf());
		ps.setString(3, p.getEmail());
		java.sql.Date sqlData = new java.sql.Date(p.getDataNascimento().getTime());
		ps.setDate(4, sqlData);
		ps.setInt(5, p.getIdPessoa());
		ps.executeUpdate();
		ps.close();
		
	}

	public boolean verificaDuplicCpf(String CPF) throws SQLException {
		boolean u = false;
		String sql = "select CPF from Pessoa where CPF = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, CPF);
		
		ResultSet rs = ps.executeQuery();

		while (rs.next() && u == false) {
			if(rs.getString("CPF").equals(CPF)) {
				u = true;
			}
		}
		
		rs.close();
		ps.close();

		return u;
	}
	
	public boolean verificaDuplicEmail(String Email) throws SQLException {
		boolean u = false;
		String sql = "select EMAIL from Pessoa where EMAIL = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, Email);
		
		ResultSet rs = ps.executeQuery();

		while (rs.next() && u == false) {
			if(rs.getString("EMAIL").equals(Email)) {
				u = true;
			}
		}
		
		rs.close();
		ps.close();

		return u;
	}

}


