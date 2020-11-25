package control;

import java.sql.SQLException;

import DAO.ClienteDao;
import DAO.EnderecoDao;
import DAO.PessoaDao;
import DAO.TelefoneDao;
import entity.Cliente;
import entity.Endereco;
import entity.Telefone;
import javafx.scene.control.TextField;

public class ControleCliente {

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

}
