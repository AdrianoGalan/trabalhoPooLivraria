package control;

import java.sql.SQLException;

import DAO.AutorDao;
import DAO.LivroDao;
import DAO.PrecoDao;
import entity.Autor;
import entity.Livro;
import entity.Preco;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;

/**
 * Classe controle do Livro
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControleLivro {

	/** Propriedade ObservableList lista*/
	private ObservableList<ModelTabelaLivro> lista;

	/**
	 * M�todo que adiciona livro na tabela LIVRO  e idLivro e idAutor na tabela LIVRO_AUTOR chamando os m�todos da classe Dao.
	 * 
	 * @param l
	 * @param idAutor
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	

	public void addLivro(Livro l, int idAutor,Preco p) throws ClassNotFoundException, SQLException {


		LivroDao ld = new LivroDao();
		l.setPrecoAtual(0);
		PrecoDao pd = new PrecoDao();
		try {
			int idlivro = ld.insereLivro(l);
			p.setFkLivroPreco(idlivro);
			p.setIdPreco(pd.inserirPreco(p));
			ld.insereLivroAutor(idlivro, idAutor);
			ld.alteraPrecoLivro(p);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * M�todo que busca livro por titulo chamando o m�todo buscaLivroTitulo() da classe Dao.
	 * 
	 * @param titulo
	 * @return buscaLivroTitulo(titulo)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Livro> buscaClienteNome(String titulo) throws ClassNotFoundException, SQLException {

		LivroDao ld = new LivroDao();
		return ld.buscaLivroTitulo(titulo);

	}

	/**
	 * M�todo que altera preco do livro chamando o m�todo alteraPrecoLivro() da classe Dao. 
	 * 
	 * @param p
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alteraPrecoLivro(Preco p) throws ClassNotFoundException, SQLException {
		LivroDao ld = new LivroDao();
		ld.alteraPrecoLivro(p);
	}

	/**
	 * M�todo que busca lirvo chamando o m�todo buscaLivro() da classe Dao. 
	 * 
	 * @param texto
	 * @param meio
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void procurarLivro(String texto, int meio) throws SQLException, ClassNotFoundException {

		LivroDao livDao = new LivroDao();
		lista = livDao.buscaLivro(texto, meio);

	}

	/**
	 * M�todo que atualiza a quantidade de livros no estoque chamando o m�todo atualizaEstoque() da classe Dao.
	 * 
	 * @param idLivro
	 * @param qtsEstoque
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void atualizaEstoque(int idLivro, int qtsEstoque) throws SQLException, ClassNotFoundException {

		LivroDao ld = new LivroDao();

		ld.atualizaEstoque(idLivro, qtsEstoque);

	}

	/**
	 * M�todo que lista os autores chamando o m�todo listarAutores() da classe Dao. 
	 * 
	 * @return ld.listarAutores()
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Autor> listarAutores() throws ClassNotFoundException, SQLException {
		AutorDao ld = new AutorDao();
		return ld.listarAutores();
	}

	/**
	 * Recupera a propriedade lista.
	 * 
	 * @return lista
	 */
	public ObservableList<ModelTabelaLivro> getLista() {
		return lista;
	}

}
