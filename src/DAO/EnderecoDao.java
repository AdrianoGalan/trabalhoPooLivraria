
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.Conexao;
import entity.Endereco;

/**
 * Classe Dao do Endereço
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class EnderecoDao {

	/** Conexao c. */
	private Connection c;

	/**
	 * Classe que recupera a conexão com o Banco.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public EnderecoDao() throws ClassNotFoundException, SQLException {
		Conexao con = new Conexao();

		c = con.getConnection();
	}

	/**
	 * Método que insere dados do endereço na tabela ENDERECO do Banco de dados.
	 * @param e
	 * @return id
	 * @throws SQLException
	 */
	public int insereEndereco(Endereco e) throws SQLException {

		int id = -1;

		String sql = "INSERT INTO ENDERECO VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, e.getRua());
		ps.setInt(2, e.getNumero());
		ps.setString(3, e.getBairro());
		ps.setString(4, e.getCidade());
		ps.setString(5, e.getEstado());
		ps.setString(6, e.getComplemento());
		ps.setString(7, e.getCep());
		ps.executeUpdate();

		// final
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			id = rs.getInt(1);
		}

		rs.close();
		ps.close();

		return id;

	}

	/**
	 * Método que busca endereço da tabela ENDERECO do banco de dados.
	 * 
	 * @param FkEndereco
	 * @return endereço
	 * @throws SQLException
	 */
	public Endereco buscaEndereco(int FkEndereco) throws SQLException {
		Endereco e = new Endereco();
		String sql = "select * from ENDERECO where ID_ENDERECO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, FkEndereco);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			e.setIdEndereco(rs.getInt("ID_ENDERECO"));
			e.setRua(rs.getString("RUA"));
			e.setNumero(rs.getInt("NUMERO"));
			e.setBairro(rs.getString("BAIRRO"));
			e.setCidade(rs.getString("CIDADE"));
			e.setEstado(rs.getString("ESTADO"));
			e.setComplemento(rs.getString("COMPLEMENTO"));
			e.setCep(rs.getString("CEP"));
		}
		return e;
	}

	/**
	 * Método que altera(atualiza) endereco
	 * 
	 * @param e
	 * @throws SQLException
	 */
	public void alteraEndereco(Endereco e) throws SQLException {
		String sql = "update ENDERECO set RUA = ?,NUMERO = ?,BAIRRO = ?, CIDADE = ?, ESTADO = ?, COMPLEMENTO = ?, CEP = ? "
				+ "where ID_ENDERECO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, e.getRua());
		ps.setInt(2, e.getNumero());
		ps.setString(3, e.getBairro());
		ps.setString(4, e.getCidade());
		ps.setString(5, e.getEstado());
		ps.setString(6, e.getComplemento());
		ps.setString(7, e.getCep());
		ps.setInt(8, e.getIdEndereco());
		ps.executeUpdate();

		ps.close();
	}

}
