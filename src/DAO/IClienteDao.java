package DAO;

import java.sql.SQLException;

import entity.Cliente;


public interface IClienteDao {
	
	public void insereCliente (Cliente cliente) throws SQLException;


}
