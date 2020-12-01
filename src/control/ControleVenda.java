package control;

import java.sql.SQLException;

import DAO.VendaDao;

/**
 * Classe controle de venda
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControleVenda {
	
	/**
	 * Método que adiciona dados na tabela venda chamando o método inserirVenda() da classe Dao.
	 * 
	 * @param idCliente
	 * @return vd.inserirVenda(idCliente)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int addVenda(int idCliente) throws ClassNotFoundException, SQLException {
		
		VendaDao vd = new VendaDao();
		
		return vd.inserirVenda(idCliente);
		
	}

}
