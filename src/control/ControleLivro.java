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

	public void addLivro(Livro l, Autor a) throws ClassNotFoundException, SQLException {

		LivroDao ld = new LivroDao();
		AutorDao ad = new AutorDao();

			ld.insereLivro(l);
			ad.insereAutor(a);
			
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

	public ObservableList<ModelTabelaLivro> getLista() {
		return lista;
	}
	
	

}
