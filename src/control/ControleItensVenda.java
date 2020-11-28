package control;

import java.sql.SQLException;

import DAO.ItensVendaDao;
import javafx.collections.ObservableList;
import tabelaModel.ModelItensVenda;

public class ControleItensVenda {
	
	public void addLivro(int idVenda, ObservableList<ModelItensVenda> itensVenda) throws SQLException, ClassNotFoundException {
		
		ItensVendaDao ivd = new ItensVendaDao();
		
		ivd.insereLivro(idVenda, itensVenda);
		
		
	}

}
