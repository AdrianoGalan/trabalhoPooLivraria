package control;


import java.sql.SQLException;

import DAO.LivroDao;
import entity.Livro;
import javafx.collections.ObservableList;

public class ControlePesquisaLivro {
	
	private ObservableList<Livro> lista;
	
	public void procurarLivro(String texto,int meio) {
		String meiosDePesq[] = {"TITULO","ISBN","a.NOME"};
		try {
			LivroDao livDao = new LivroDao();
			lista = livDao.buscaLivro(texto,meiosDePesq[meio]);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Livro> getLista() {
		return lista;
	}
	
	

}
