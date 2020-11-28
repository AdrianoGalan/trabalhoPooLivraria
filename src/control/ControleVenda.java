package control;

import java.sql.SQLException;

import DAO.VendaDao;

public class ControleVenda {
	
	public int addVenda(int idCliente) throws ClassNotFoundException, SQLException {
		
		VendaDao vd = new VendaDao();
		
		return vd.inserirVenda(idCliente);
		
	}

}
