package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Funcionario;


public class FuncionarioDao {
	

	private Connection c;

	public FuncionarioDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();

		c = con.getConnection();

	}

	public void insereFuncionario(Funcionario f) throws SQLException {
		String sql = "INSERT INTO USUARIO VALUES(?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, f.getCargo());
		ps.setString(2, f.getMatricula());
		ps.setDate(3, f.getDataAsmissao());
		ps.setInt(4, f.getFkPessoaFuncionario());

		ps.execute();
		ps.close();
		

	}

	public Funcionario buscaFuncionarioId(int id) throws SQLException {

		Funcionario f = new Funcionario();

		String sql = "SELECT ID_FUNCIONARIO, CARGO, MATRICULA, DATA_ADMISSAO, "
				+ "FK_PESSOA_FUNCIONARIO FROM FUNCIONARIO  WHERE ID_FUNCIONARIO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			f.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			f.setCargo(rs.getString("CARGO"));
			f.setMatricula(rs.getString("MATRICULA"));
			f.setDataAsmissao(rs.getDate("DATA_ADMISSAO"));
			f.setFkPessoaFuncionario(rs.getInt("FK_PESSOA_FUNCIONARIO"));

		}

		rs.close();
		ps.close();

		return f;

	}

	


}