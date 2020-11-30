package control;

import java.sql.SQLException;

import DAO.RelatorioDao;
import javafx.collections.ObservableList;
import tabelaModel.ModelTabelaLivro;

/**
 * Classe controle do Relatorio.
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControleRelatorio {

	/**
	 * Método que busca os livros mais vendidos chamando o método livroMaisVendido() da classe Dao.
	 * 
	 * @return rd.livroMaisVendido()
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<ModelTabelaLivro> livroMaisVendido() throws SQLException, ClassNotFoundException {

		RelatorioDao rd = new RelatorioDao();

		return rd.livroMaisVendido();

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<ModelTabelaLivro> livroMenosVendido() throws SQLException, ClassNotFoundException {

		RelatorioDao rd = new RelatorioDao();

		return rd.livroMenosVendido();

	}

	public ObservableList<ModelTabelaLivro> livroEstoqueBaixo() throws ClassNotFoundException, SQLException {

		RelatorioDao rd = new RelatorioDao();

		return rd.livroEstoqueBaixo();
	}

	public ObservableList<ModelTabelaLivro> dataVenda() throws ClassNotFoundException, SQLException {

		RelatorioDao rd = new RelatorioDao();

		return rd.dataVenda();
	}

	public int melhorDiaSemana() throws ClassNotFoundException, SQLException {

		RelatorioDao rd = new RelatorioDao();

		return rd.melhorDiaSemana();

	}

}
