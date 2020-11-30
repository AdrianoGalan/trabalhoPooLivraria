package control;

import java.sql.SQLException;

import DAO.AutorDao;
import entity.Autor;
import javafx.collections.ObservableList;

public class ControleAutor {
	
		public void addAutor (Autor a) throws ClassNotFoundException, SQLException {
			AutorDao ad = new AutorDao();
			
			try {
				ad.insereAutor(a);
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
				System.out.println("1");
			} 
		}
		
		public boolean verificaDuplicata(String nome) throws SQLException, ClassNotFoundException {
			AutorDao ad = new AutorDao();
			return ad.verificaDuplicNome(nome);
		}
		
		public ObservableList<Autor> buscaClienteNome(String nome) throws ClassNotFoundException, SQLException{
			
			AutorDao ad = new AutorDao();
			
			return  ad.pesquisarAutores(nome);
			
		}
		
		public void alterarAutor(Autor a) throws ClassNotFoundException, SQLException {
			AutorDao ad = new AutorDao();
			ad.alteraAutor(a);
			
		}
		
}
