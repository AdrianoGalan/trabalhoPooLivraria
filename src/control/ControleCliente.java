package control;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import DAO.ClienteDao;
import DAO.EnderecoDao;
import DAO.PessoaDao;
import DAO.TelefoneDao;
import entity.Cliente;
import entity.Endereco;
import entity.Telefone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class ControleCliente {
	private ObservableList<Cliente> lista;
	
	private IntegerProperty idCliente;
	private StringProperty nome;
	private StringProperty cpf;
	private StringProperty email;
	private ObjectProperty<GregorianCalendar> dataNascimento;
	private GregorianCalendar dataCadastro;
	
	public ObservableList<Cliente> buscaClienteNome(String nome) throws ClassNotFoundException, SQLException{
		
		ClienteDao cd = new ClienteDao();
		
		
		return  cd.buscaClienteNome(nome);
		
		
	}
	
	public Cliente buscaClienteCpf(String cpf) throws ClassNotFoundException, SQLException{
		
		ClienteDao cd = new ClienteDao();
		
		
		return  cd.buscaClienteCpf(cpf);
		
		
	}
	public void buscaClientesNome(String nome) throws SQLException, ClassNotFoundException {
		
			ClienteDao clienteDAO = new ClienteDao();
			lista = clienteDAO.buscaClienteNome(nome);
			
	}

	public void addCliente(Cliente c, Endereco e, Telefone t) throws ClassNotFoundException, SQLException {

		ClienteDao cd = new ClienteDao();
		TelefoneDao td = new TelefoneDao();
		PessoaDao pd = new PessoaDao();
		EnderecoDao ed = new EnderecoDao();

		try {
			c.setFkEdetecoPessoa(ed.insereEndereco(e));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("1");
		}
		try {
			c.setFkPessoaCliente(pd.inserePessoa(c));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("2");
		}
		t.setFkPessoaTelefone(c.getFkPessoaCliente());
		try {
			td.insereTelefone(t);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("3");
		}
		try {
			cd.insereCliente(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("4");
		}
		

	}
	
	public void alterarCliente(Cliente c,Endereco e,Telefone t) throws ClassNotFoundException, SQLException {
		
		TelefoneDao td = new TelefoneDao();
		PessoaDao pd = new PessoaDao();
		EnderecoDao ed = new EnderecoDao();
		
		ed.alteraEndereco(e);
		td.atualizaTelefone(c.getIdPessoa(), t);
		pd.atualizaPessoa(c);
		
		
	}
	
	public Telefone buscaTelefoneClient(int idPessoaFunc) throws ClassNotFoundException, SQLException {
		TelefoneDao td = new TelefoneDao();
		Telefone t = td.buscaTelefone(idPessoaFunc);
		return t;
	}
	
	public Endereco buscaEnderecoClient(int FKEndereco) throws ClassNotFoundException, SQLException {
		EnderecoDao ed = new EnderecoDao();
		Endereco e = ed.buscaEndereco(FKEndereco);
		return e;
	}
	
	
	public void deleteCliente (Cliente c, Endereco e, Telefone t) throws ClassNotFoundException, SQLException {
		
	}
	
	public ObservableList<Cliente> getLista() {
		return lista;
	}

	public IntegerProperty getIdCliente() {
		return idCliente;
	}

	public StringProperty getNome() {
		return nome;
	}

	public StringProperty getCpf() {
		return cpf;
	}

	public StringProperty getEmail() {
		return email;
	}

	public ObjectProperty<GregorianCalendar> getDataNascimento() {
		return dataNascimento;
	}

	public GregorianCalendar getDataCadastro() {
		return dataCadastro;
	}

}
