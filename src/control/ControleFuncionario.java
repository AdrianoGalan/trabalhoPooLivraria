package control;

import java.sql.SQLException;

import DAO.ClienteDao;
import DAO.EnderecoDao;
import DAO.FuncionarioDao;
import DAO.PessoaDao;
import DAO.TelefoneDao;
import entity.Cliente;
import entity.Endereco;
import entity.Funcionario;
import entity.Pessoa;
import entity.Telefone;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class ControleFuncionario {

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
	
	public ObservableList<Funcionario> buscarFuncionariosNome(String nome) throws SQLException, ClassNotFoundException{
		FuncionarioDao fd = new FuncionarioDao();
		ObservableList<Funcionario> lista = fd.buscaFuncionarioNome(nome); 
		return lista;
		
	}
	
	public Telefone buscaTelefoneFunc(int idPessoaFunc) throws ClassNotFoundException, SQLException {
		TelefoneDao td = new TelefoneDao();
		Telefone t = td.buscaTelefone(idPessoaFunc);
		return t;
	}
	
	public Endereco buscaEnderecoFunc(int FKEndereco) throws ClassNotFoundException, SQLException {
		EnderecoDao ed = new EnderecoDao();
		Endereco e = ed.buscaEndereco(FKEndereco);
		return e;
	}
	

}
