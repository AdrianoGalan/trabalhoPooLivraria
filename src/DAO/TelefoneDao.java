package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import connection.Conexao;
import entity.Telefone;

/**
 * Classe Dao do Telefone
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class TelefoneDao {

	/** Conexao c. */
	private Connection c;

	/**
	 * Classe que recupera a conexao com o Banco.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TelefoneDao() throws ClassNotFoundException, SQLException {
		Conexao con = new Conexao();

		c = con.getConnection();
	}

	/**
	 * Metodo que insere dados na tabela TELEFONE do banco de dados.
	 * 
	 * @param t
	 * @throws SQLException
	 */
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
	
	/**
	 * Metodo que faz busca(pesquisa) de telefone de uma pessoa atraves do id.
	 * 
	 * @param idPessoa
	 * @return telefone
	 * @throws SQLException
	 */
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
	
	/**
	 * Metodo que altera(atualiza) telefone de uma pessoa.
	 * @param idPessoa
	 * @param telefone
	 * @throws SQLException
	 */
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
