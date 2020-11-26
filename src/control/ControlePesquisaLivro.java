package control;


import java.sql.SQLException;

import DAO.LivroDao;
import entity.Livro;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;

public class ControlePesquisaLivro {
	
	private ObservableList<ModelTabelaLivro> lista;
	
	public void procurarLivro(String texto,int meio) {
		
		try {
			LivroDao livDao = new LivroDao();
			lista = livDao.buscaLivro(texto,meio);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<ModelTabelaLivro> getLista() {
		return lista;
	}
	
	

}
