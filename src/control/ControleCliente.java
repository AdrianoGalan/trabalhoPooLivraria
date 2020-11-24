package control;

import java.sql.SQLException;

import DAO.ClienteDao;
import entity.Cliente;
import javafx.scene.control.TextField;

public class ControleCliente implements IClienteController{

	private TextField tfIdCliente;
	private TextField tfNomeCliente;
	private TextField tfCpfCliente;
	private TextField tfTelefoneCliente;
	private TextField tfNumCliente;
	private TextField tfRuaCliente;
	private TextField tfLogradouroCliente; 
	private TextField tfBairroCliente; 
	private TextField tfEmailCliente; 
	private TextField tfDtnascCliente;
	
	

	public ControleCliente(TextField tfNome, TextField tfCpf, TextField tfTelefone, TextField tfNum, TextField tfRua,
			TextField tfLogradouro, TextField tfBairro, TextField tfEmail, TextField tfDtnasc) {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void inserirCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
		ClienteDao cDao = new ClienteDao();
		cDao.insereCliente(cliente);
		limpaCamposCliente();
	}


	private void limpaCamposCliente() {
		tfIdCliente.setText("");
		tfNomeCliente.setText("");
		tfCpfCliente.setText("");
		tfTelefoneCliente.setText("");
		tfNumCliente.setText("");
		tfRuaCliente.setText("");
		tfLogradouroCliente.setText("");
		tfBairroCliente.setText("");
		tfEmailCliente.setText("");
		tfDtnascCliente.setText("");
	}

}
