package control;

import java.sql.SQLException;

import entity.Cliente;

public interface IClienteController {
	
	public void inserirCliente(Cliente cliente) throws ClassNotFoundException, SQLException;

}
