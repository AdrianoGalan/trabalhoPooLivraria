package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.Conexao;
import entity.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Dao do Funcionario
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class FuncionarioDao {
	
	/** Conexao c. */
	private Connection c;

	/**
	 * Classe que recupera a conexao com o Banco.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();

		c = con.getConnection();

	}

	/**
	 * Metodo que insere dados do funcionario na tabela FUNCIONARIO do Banco de dados.
	 * 
	 * @param f
	 * @throws SQLException
	 */
	public void insereFuncionario(Funcionario f) throws SQLException {
		
		java.sql.Date sqlData = new java.sql.Date(f.getDataAdmissao().getTime());
		
		String sql = "INSERT INTO FUNCIONARIO VALUES(?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setString(1, f.getCargo());
		ps.setString(2, f.getMatricula());
		ps.setDate(3, sqlData);
		ps.setInt(4, f.getFkPessoaFuncionario());
		ps.execute();
		ps.close();
		
	}

	/**
	 * Metodo que faz busca do funcionario por id.
	 * 
	 * @param id
	 * @return funcionario
	 * @throws SQLException
	 */
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
			f.setDataAdmissao(new java.util.Date(rs.getDate("DATA_ADMISSAO").getTime()));
			f.setFkPessoaFuncionario(rs.getInt("FK_PESSOA_FUNCIONARIO"));

		}

		rs.close();
		ps.close();

		return f;

	}
	
	/**
	 * Metodo que faz busca do funcionario por nome.
	 * 
	 * @param nome
	 * @return lista
	 * @throws SQLException
	 */
	public ObservableList<Funcionario> buscaFuncionarioNome(String nome) throws SQLException {

		ObservableList<Funcionario> lista = FXCollections.observableArrayList();

		String sql = "select * from FUNCIONARIO f, PESSOA p "
				+ "where f.FK_PESSOA_fUNCIONARIO = p.ID_PESSOA and NOME like ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, nome+"%");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Funcionario f = new Funcionario();
			f.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			f.setIdPessoa(rs.getInt("ID_PESSOA"));
			f.setNome(rs.getString("NOME"));
			f.setCpf(rs.getString("CPF"));
			f.setFkEdetecoPessoa(rs.getInt("FK_EDERECO_PESSOA"));
			f.setCargo(rs.getString("CARGO"));
			f.setEmail(rs.getString("EMAIL"));
			f.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
			f.setCargo(rs.getString("CARGO"));
			f.setMatricula(rs.getString("MATRICULA"));
			f.setDataAdmissao((rs.getDate("DATA_ADMISSAO")));
			f.setFkPessoaFuncionario(rs.getInt("FK_PESSOA_FUNCIONARIO"));
			lista.add(f);
		}

		rs.close();
		ps.close();

		return lista;

	}
	
	/**
	 * Metodo que busca lista de funcionario.
	 * 
	 * @return lista
	 * @throws SQLException
	 */
	public ObservableList<Funcionario> getListaFuncionario() throws SQLException {

		
		ObservableList<Funcionario> lista = FXCollections.observableArrayList();
		

		String sql = "SELECT p.NOME,f.* "
				+ " FROM FUNCIONARIO f, PESSOA p where f.FK_PESSOA_fUNCIONARIO = p.ID_PESSOA ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Funcionario f = new Funcionario();
			f.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			f.setNome(rs.getString("NOME"));
			f.setCargo(rs.getString("CARGO"));
			f.setMatricula(rs.getString("MATRICULA"));
			f.setDataAdmissao(new java.util.Date(rs.getDate("DATA_ADMISSAO").getTime()));
			f.setFkPessoaFuncionario(rs.getInt("FK_PESSOA_FUNCIONARIO"));
			lista.add(f);
		}

		rs.close();
		ps.close();

		return lista;

	}
	
	/**
	 * Metodo que altera(atualiza) funcionario.
	 * 
	 * @param f
	 * @throws SQLException
	 */
	public void atualizaFuncionario(Funcionario f) throws SQLException {
		String sql = "update FUNCIONARIO set CARGO = ?, MATRICULA = ?, DATA_ADMISSAO = ? where ID_FUNCIONARIO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, f.getCargo());
		ps.setString(2, f.getMatricula());
		java.sql.Date sqlData = new java.sql.Date(f.getDataAdmissao().getTime());
		ps.setDate(3, sqlData);
		ps.setInt(4, f.getIdFuncionario());
		ps.executeUpdate();
		ps.close();
		
	}

	


}
