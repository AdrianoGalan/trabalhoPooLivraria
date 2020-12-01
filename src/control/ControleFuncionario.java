package control;

import java.sql.SQLException;

import DAO.EnderecoDao;
import DAO.FuncionarioDao;
import DAO.PessoaDao;
import DAO.TelefoneDao;
import entity.Endereco;
import entity.Funcionario;
import entity.Telefone;
import javafx.collections.ObservableList;

/**
 * Classe controle do Funcionario
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControleFuncionario {

	/**
	 * M�todo que adiciona funcionario, endereco e telefone chamando m�todos de inser��o da classe Dao.
	 * 
	 * @param f
	 * @param e
	 * @param t
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addFuncionario(Funcionario f, Endereco e, Telefone t) throws ClassNotFoundException, SQLException {

		FuncionarioDao fd = new FuncionarioDao();
		TelefoneDao td = new TelefoneDao();
		PessoaDao pd = new PessoaDao();
		EnderecoDao ed = new EnderecoDao();

		try {
			f.setFkEdetecoPessoa(ed.insereEndereco(e));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("1");
		}
		
		try {	
			f.setFkPessoaFuncionario(pd.inserePessoa(f));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("2");
		}
			t.setFkPessoaTelefone(f.getFkPessoaFuncionario());
		try {
			td.insereTelefone(t);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("3");
		}
		try {
			fd.insereFuncionario(f);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("4");
		}
		
		
		f.setFkEdetecoPessoa(ed.insereEndereco(e));
		

	}
	
	/**
	 * M�todo que altera funcionario, endereco e telefone chamando m�todos de altera��o(atualiza��o) das classes Dao.
	 * 
	 * @param f
	 * @param e
	 * @param t
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarFuncionario(Funcionario f,Endereco e,Telefone t) throws ClassNotFoundException, SQLException {
		
		FuncionarioDao fd = new FuncionarioDao();
		TelefoneDao td = new TelefoneDao();
		PessoaDao pd = new PessoaDao();
		EnderecoDao ed = new EnderecoDao();
		
		ed.alteraEndereco(e);
		td.atualizaTelefone(f.getIdPessoa(), t);
		pd.atualizaPessoa(f);
		fd.atualizaFuncionario(f);
		
	}
	
	/**
	 * M�todo que busca nome do funcionario chamando o m�todo buscaFuncionarioNome() da classe Dao.
	 * 
	 * @param nome
	 * @return lista
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<Funcionario> buscarFuncionariosNome(String nome) throws SQLException, ClassNotFoundException{
		FuncionarioDao fd = new FuncionarioDao();
		ObservableList<Funcionario> lista = fd.buscaFuncionarioNome(nome); 
		return lista;
		
	}
	
	/**
	 * M�todo que busca telefone do funcionario chamando o m�todo buscaTelefone() da classe Dao.
	 * 
	 * @param idPessoaFunc
	 * @return telefone
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Telefone buscaTelefoneFunc(int idPessoaFunc) throws ClassNotFoundException, SQLException {
		TelefoneDao td = new TelefoneDao();
		Telefone t = td.buscaTelefone(idPessoaFunc);
		return t;
	}
	
	/**
	 * M�todo que busca endereco do funcionario chamando o m�todo buscaEndereco() da classe Dao.
	 * 
	 * @param FKEndereco
	 * @return endereco
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Endereco buscaEnderecoFunc(int FKEndereco) throws ClassNotFoundException, SQLException {
		EnderecoDao ed = new EnderecoDao();
		Endereco e = ed.buscaEndereco(FKEndereco);
		return e;
	}
	

}
