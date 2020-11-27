package control;

import java.sql.SQLException;

import DAO.AutorDao;
import DAO.LivroDao;
import entity.Autor;
import entity.Livro;

public class ControleLivro {

	public void addLivro (Livro l, Autor a) throws ClassNotFoundException, SQLException{
		
		LivroDao ld = new LivroDao();
		AutorDao ad = new AutorDao();
		
		try {
			ld.insereLivro(l);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			System.out.println("1");
		}
		
		try {
			ad.insereAutor(a);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			System.out.println("2");
		}
		
	}
	
}
