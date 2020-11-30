package control;

import java.sql.SQLException;

import DAO.ItensVendaDao;
import javafx.collections.ObservableList;
import tabelaModel.ModelItensVenda;

/**
 * Classe controle de itens venda
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControleItensVenda {
	
	/**
	 * Método que insere livro na tabela ITENS_VENDA  chamando o método insereLivro() da classe Dao
	 * 
	 * @param idVenda
	 * @param itensVenda
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void addLivro(int idVenda, ObservableList<ModelItensVenda> itensVenda) throws SQLException, ClassNotFoundException {
		
		ItensVendaDao ivd = new ItensVendaDao();
		
		ivd.insereLivro(idVenda, itensVenda);
		
		
	}

}
