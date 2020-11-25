package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
