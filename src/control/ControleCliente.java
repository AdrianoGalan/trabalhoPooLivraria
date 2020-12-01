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
/**
 * Classe controle do Cliente
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControleCliente {
	
	/** Propriedade ObservableList lista*/
	private ObservableList<Cliente> lista;
	
	/** Propriedade IntegerProperty idCliente*/
	private IntegerProperty idCliente;
	
	/** Propriedade StringProperty nome*/
	private StringProperty nome;
	
	/** Propriedade StringProperty cpf*/
	private StringProperty cpf;
	
	/** Propriedade StringProperty email*/
	private StringProperty email;
	
	/** Propriedade ObjectProperty dataNascimento*/
	private ObjectProperty<GregorianCalendar> dataNascimento;
	
	/** Propriedade GregorianCalendar dataCadastro*/
	private GregorianCalendar dataCadastro;
	
	/**
	 * Metodo que busca Cliente por nome chamando o metodo buscaClienteNome() da classe Dao
	 * 
	 * @param nome
	 * @return cd.buscaClienteNome(nome)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Cliente> buscaClienteNome(String nome) throws ClassNotFoundException, SQLException{
		
		ClienteDao cd = new ClienteDao();
		
		
		return  cd.buscaClienteNome(nome);
		
		
	}
	
	/**
	 * Metodo que busca cliente por cpf chamando o metodo buscaClienteCpf() da classse Dao
	 * 
	 * @param cpf
	 * @return buscaClienteCpf(cpf)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Cliente buscaClienteCpf(String cpf) throws ClassNotFoundException, SQLException{
		
		ClienteDao cd = new ClienteDao();
		
		
		return  cd.buscaClienteCpf(cpf);
		
		
	}
	
	/**
	 * Metodo que busca aa lista de clientes por nome chamando o metodo buscaClienteNome() da classe Dao
	 * 
	 * @param nome
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void buscaClientesNome(String nome) throws SQLException, ClassNotFoundException {
		
			ClienteDao clienteDAO = new ClienteDao();
			lista = clienteDAO.buscaClienteNome(nome);
			
	}

	/**
	 * Metodo que adiciona dados do cliente, endereco e telefone chamando metodos de insercao da classes Dao.
	 * 
	 * @param c
	 * @param e
	 * @param t
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
	
	/**
	 * Metodo que altera cliente, endereco e telefone chamando metodos de alteracao(atualizacao) das Classes Dao.
	 * 
	 * @param c
	 * @param e
	 * @param t
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarCliente(Cliente c,Endereco e,Telefone t) throws ClassNotFoundException, SQLException {
		
		TelefoneDao td = new TelefoneDao();
		PessoaDao pd = new PessoaDao();
		EnderecoDao ed = new EnderecoDao();
		
		ed.alteraEndereco(e);
		td.atualizaTelefone(c.getIdPessoa(), t);
		pd.atualizaPessoa(c);
		
		
	}
	
	/**
	 * Metodo que busca telefone chamando metodo buscaTelefone() da classe Dao.
	 * 
	 * @param idPessoaFunc
	 * @return td.buscaTelefone(idPessoaFunc)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Telefone buscaTelefoneClient(int idPessoaFunc) throws ClassNotFoundException, SQLException {
		TelefoneDao td = new TelefoneDao();
		Telefone t = td.buscaTelefone(idPessoaFunc);
		return t;
	}
	
	/**
	 * Metodo que busca endereco chamando o metodo buscaEndereco() da classe Dao.
	 * 
	 * @param FKEndereco
	 * @return ed.buscaEndereco(FKEndereco)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Endereco buscaEnderecoClient(int FKEndereco) throws ClassNotFoundException, SQLException {
		EnderecoDao ed = new EnderecoDao();
		Endereco e = ed.buscaEndereco(FKEndereco);
		return e;
	}
	
	/**
	 * Metodo que deleta cliente
	 * 
	 * @param c
	 * @param e
	 * @param t
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteCliente (Cliente c, Endereco e, Telefone t) throws ClassNotFoundException, SQLException {
		
	}
	
	/**
	 * Recupera a propriedade lista.
	 * 
	 * @return lista
	 */
	public ObservableList<Cliente> getLista() {
		return lista;
	}

	/**
	 * Recupera a propriedade idCliente.
	 * 
	 * @return id do cliente
	 */
	public IntegerProperty getIdCliente() {
		return idCliente;
	}

	/**
	 * Recupera a propriedade nome.
	 * 
	 * @return nome
	 */
	public StringProperty getNome() {
		return nome;
	}

	/**
	 * Recupera a propriedade cpf.
	 * 
	 * @return cpf
	 */
	public StringProperty getCpf() {
		return cpf;
	}

	/**
	 * Recupera a propriedade email.
	 * 
	 * @return email
	 */
	public StringProperty getEmail() {
		return email;
	}

	/**
	 * Recupera a propriedade dataNascimento.
	 * 
	 * @return dataNascimento
	 */
	public ObjectProperty<GregorianCalendar> getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Recupera a propriedade dataCadastro.
	 * 
	 * @return dataCadastro
	 */
	public GregorianCalendar getDataCadastro() {
		return dataCadastro;
	}

}
