package control;

import java.sql.SQLException;

import DAO.AutorDao;
import DAO.ClienteDao;
import DAO.LivroDao;
import entity.Autor;
import entity.Cliente;
import entity.Livro;
import javafx.collections.ObservableList;

public class ControleLivro {

	public void addLivro(Livro l, Autor a) throws ClassNotFoundException, SQLException {

		LivroDao ld = new LivroDao();
		AutorDao ad = new AutorDao();

		ld.insereLivro(l);
		ad.insereAutor(a);

	}

	public ObservableList<Livro> buscaClienteNome(String titulo) throws ClassNotFoundException, SQLException {

		LivroDao ld = new LivroDao();

		return ld.buscaLivroTitulo(titulo);

	}

}
