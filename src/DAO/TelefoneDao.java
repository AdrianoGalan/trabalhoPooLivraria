package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import connection.Conexao;
import entity.Telefone;

public class TelefoneDao {

	private Connection c;

	public TelefoneDao() throws ClassNotFoundException, SQLException {
		Conexao con = new Conexao();

		c = con.getConnection();
	}

	public void insereTelefone(Telefone t) throws SQLException {


		String sql = "INSERT INTO TELEFONE VALUES(?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getTipo());
		ps.setString(2, t.getDdd());
		ps.setString(3, t.getNumero());
		ps.setInt(4, t.getFkPessoaTelefone());
		ps.executeUpdate();

	

		ps.close();

	}
	
	public Telefone buscaTelefone(int idPessoa) throws SQLException {
		Telefone t = new Telefone();
		String sql = "select * from TELEFONE where FK_PESSOA_TELEFONE = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, idPessoa);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			t.setDdd(rs.getString("DDD")); 
			t.setNumero(rs.getString("TELEFONE"));
			t.setTipo(rs.getString("TIPO"));
		}
		
		return t;
	}
	
	public void atualizaTelefone(int idPessoa,Telefone t) throws SQLException {
		String sql = "update TELEFONE set TIPO = ?,DDD = ?, TELEFONE = ? WHERE FK_PESSOA_TELEFONE = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getTipo());
		ps.setString(2, t.getDdd());
		ps.setString(3, t.getNumero());
		ps.setInt(4, idPessoa);
		ps.executeUpdate();
		ps.close();
		
	}
	
	
	
	
	

}
