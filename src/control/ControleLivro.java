package control;

import java.sql.SQLException;

import DAO.AutorDao;
import DAO.LivroDao;
import entity.Autor;
import entity.Livro;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;

public class ControleLivro {

	private ObservableList<ModelTabelaLivro> lista;

	public void addLivro(Livro l, Autor a) throws ClassNotFoundException, SQLException {

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

	public ObservableList<Livro> buscaClienteNome(String titulo) throws ClassNotFoundException, SQLException {

		LivroDao ld = new LivroDao();
		return ld.buscaLivroTitulo(titulo);

	}
	
	public void alteraPrecoLivro(Double preco, int ISBN) throws ClassNotFoundException, SQLException {
		LivroDao ld = new LivroDao();
		ld.alteraPrecoLivro(preco, ISBN);
	}

	public void procurarLivro(String texto, int meio) throws SQLException, ClassNotFoundException {

		LivroDao livDao = new LivroDao();
		lista = livDao.buscaLivro(texto, meio);

	}

	public ObservableList<ModelTabelaLivro> getLista() {
		return lista;
	}
	
	

}
