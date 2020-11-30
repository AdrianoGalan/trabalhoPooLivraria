package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteDao {
	
	private Connection c;

	public ClienteDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();
	}


	public void insereCliente(Cliente cliente) throws SQLException {
		
		String sql = "INSERT INTO CLIENTE VALUES ( GETDATE(), ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cliente.getFkPessoaCliente());
	
		ps.executeUpdate();

		ps.close();
	}
	
	public void atualizaCliente(Cliente cliente) throws SQLException {
		
		String sql = "UPDATE CLIENTE c SET nome = ? WHERE c.FK_PESSOA_CLIENTE = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cliente.getFkPessoaCliente());

		ps.executeUpdate();
		
		ps.close();
		
	}
	

	
	public void excluiCliente(Cliente cliente) throws SQLException {
		
		String sql = "DELETE CLIENTE c WHERE c.FK_PESSOA_CLIENTE = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cliente.getFkPessoaCliente());

		ps.execute();
		ps.close();
	}

	public ObservableList<Cliente> buscaClienteNome(String nome) throws SQLException {

		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" Select c.ID_CLIENTE,p.ID_PESSOA,p.NOME,p.EMAIL,p.CPF,p.DATA_NASCIMENTO, ");
		sql.append("c.DATA_CADASTRO, c.FK_PESSOA_CLIENTE,p.FK_EDERECO_PESSOA AS FK_ENDERECO ");
		sql.append(" From CLIENTE c INNER JOIN PESSOA p " );
		sql.append(" ON C.FK_PESSOA_CLIENTE = P.ID_PESSOA  ");
		sql.append(" WHERE p.NOME LIKE ? ");

	
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, "%" + nome + "%");
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			cliente = new Cliente();
			cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
			cliente.setIdPessoa(rs.getInt("ID_PESSOA"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setCpf(rs.getString("CPF"));

			cliente.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
			cliente.setDataCadastro(rs.getDate("DATA_CADASTRO"));

			cliente.setFkPessoaCliente(rs.getInt("FK_PESSOA_CLIENTE"));
			cliente.setFkEdetecoPessoa(rs.getInt("FK_ENDERECO"));
			lista.add(cliente);

		}

		rs.close();
		ps.close();

		return lista;

	}
	
	public Cliente buscaClienteCpf(String cpf) throws SQLException {
		
		Cliente cliente = new Cliente();
	
		
		StringBuilder sql = new StringBuilder();
		sql.append(" Select c.ID_CLIENTE,p.ID_PESSOA,p.NOME,p.EMAIL,p.CPF,p.DATA_NASCIMENTO, ");
		sql.append("c.DATA_CADASTRO, c.FK_PESSOA_CLIENTE,p.FK_EDERECO_PESSOA AS FK_ENDERECO ");
		sql.append(" From CLIENTE c INNER JOIN PESSOA p " );
		sql.append(" ON C.FK_PESSOA_CLIENTE = P.ID_PESSOA  ");
		sql.append(" WHERE p.CPF = ? ");
		
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, cpf);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			
			
			cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
			cliente.setIdPessoa(rs.getInt("ID_PESSOA"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setCpf(rs.getString("CPF"));
			
			cliente.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
			cliente.setDataCadastro(rs.getDate("DATA_CADASTRO"));
			
			cliente.setFkPessoaCliente(rs.getInt("FK_PESSOA_CLIENTE"));
			cliente.setFkEdetecoPessoa(rs.getInt("FK_ENDERECO"));
			
		}
		
		rs.close();
		ps.close();
		
		return cliente;
		
	}

}