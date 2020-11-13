package control;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import DAO.ClienteDao;
import entity.Cliente;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class ControlePesquisaCliente {
	
	private ObservableList<Cliente> lista;
	
	private IntegerProperty idCliente;
	private StringProperty nome;
	private StringProperty cpf;
	private StringProperty email;
	private ObjectProperty<GregorianCalendar> dataNascimento;
	private GregorianCalendar dataCadastro;

	
	public void procurarClientes(String nome) {
		try {
			ClienteDao clienteDAO = new ClienteDao();
			lista = clienteDAO.buscaClienteNome(nome);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
