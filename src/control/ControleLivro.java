package control;

import java.sql.SQLException;

import DAO.AutorDao;
import DAO.LivroDao;
import entity.Autor;
import entity.Livro;
import entity.Preco;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;

public class ControleLivro {

	private ObservableList<ModelTabelaLivro> lista;

	public void addLivro(Livro l, int idAutor) throws ClassNotFoundException, SQLException {

		LivroDao ld = new LivroDao();

		try {
			int idlivro = ld.insereLivro(l);
			ld.insereLivroAutor(idlivro, idAutor);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public ObservableList<Livro> buscaClienteNome(String titulo) throws ClassNotFoundException, SQLException {

		LivroDao ld = new LivroDao();
		return ld.buscaLivroTitulo(titulo);

	}

	public void alteraPrecoLivro(Preco p) throws ClassNotFoundException, SQLException {
		LivroDao ld = new LivroDao();
		ld.alteraPrecoLivro(p);
	}

	public void procurarLivro(String texto, int meio) throws SQLException, ClassNotFoundException {

		LivroDao livDao = new LivroDao();
		lista = livDao.buscaLivro(texto, meio);

	}

	public void atualizaEstoque(int idLivro, int qtsEstoque) throws SQLException, ClassNotFoundException {

		LivroDao ld = new LivroDao();

		ld.atualizaEstoque(idLivro, qtsEstoque);

	}

	public ObservableList<Autor> listarAutores() throws ClassNotFoundException, SQLException {
		AutorDao ld = new AutorDao();
		return ld.listarAutores();
	}

	public ObservableList<ModelTabelaLivro> getLista() {
		return lista;
	}

}
