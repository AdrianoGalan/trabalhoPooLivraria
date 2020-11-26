package control;

import java.sql.SQLException;

import DAO.AutorDao;
import entity.Autor;

public class ControleAutor {
		public void addAutor (Autor a) throws ClassNotFoundException, SQLException {
			AutorDao ad = new AutorDao();
			
		/*	try {
				a.setIdAutor(ad.insereAutor(a));
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
				System.out.println("1");
			} */
		}
}
