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
	
	public ObservableList<Funcionario> buscarFuncionariosNome(String nome) throws SQLException, ClassNotFoundException{
		FuncionarioDao fd = new FuncionarioDao();
		ObservableList<Funcionario> lista = fd.buscaFuncionarioNome(nome); 
		return lista;
		
	}
	

}
