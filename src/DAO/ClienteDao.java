package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import connection.Conexao;
import entity.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClienteDao implements IClienteDao{
	private Connection c;

	public ClienteDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();
	}
	
	@Override
	public void insereCliente(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO professor VALUES (?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cliente.getIdCliente());
		ps.setString(2, cliente.getNome());
		ps.setString(3, cliente.getCpf());
		ps.setString(4, cliente.getEmail());
		ps.setInt(5, cliente.getFkEdetecoPessoa());
		

		ps.execute();
		ps.close();
	}

	
	public ObservableList<Cliente> buscaClienteNome(String nome) throws SQLException {

		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		String sql = " Select c.ID_CLIENTE,p.ID_PESSOA,p.NOME,p.EMAIL,p.CPF,p.DATA_NASCIMENTO,c.DATA_CADASTRO,c.FK_PESSOA_CLIENTE,p.FK_EDERECO_PESSOA AS FK_ENDERECO" +
				" From CLIENTE c,PESSOA p" +
				" WHERE c.FK_PESSOA_CLIENTE = p.ID_PESSOA AND p.NOME LIKE ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, nome+"%");
		ResultSet rs = ps.executeQuery();
		Date cal1 = new Date();
		Date cal2 = new Date();
		while (rs.next()) {
			cliente = new Cliente();
			cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
			cliente.setIdPessoa(rs.getInt("ID_PESSOA"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setCpf(rs.getString("CPF"));
			cal1 = (rs.getDate("DATA_NASCIMENTO"));
			cliente.setDataNascimento(cal1);
			cal2 = (rs.getDate("DATA_CADASTRO"));
			cliente.setDataCadastro(cal2);
			cliente.setFkPessoaCliente(rs.getInt("FK_PESSOA_CLIENTE"));
			cliente.setFkEdetecoPessoa(rs.getInt("FK_ENDERECO"));
			lista.add(cliente);
			
		}

		rs.close();
		ps.close();

		return lista;

	}
	
}
