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
	 * Método que busca os livros menos vendidos chamando o método livroMenosVendido() da classe Dao. 
	 * 
	 * @return rd.livroMenosVendido()
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ObservableList<ModelTabelaLivro> livroMenosVendido() throws SQLException, ClassNotFoundException {

		RelatorioDao rd = new RelatorioDao();

		return rd.livroMenosVendido();

	}

	/**
	 * Método que busca livros com estoque menor que 5 chamando o método livroEstoqueBaixo() da classe Dao.
	 * 
	 * @return rd.livroEstoqueBaixo()
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<ModelTabelaLivro> livroEstoqueBaixo() throws ClassNotFoundException, SQLException {

		RelatorioDao rd = new RelatorioDao();

		return rd.livroEstoqueBaixo();
	}

	/**
	 * Método que busca a data dos ultimos livros vendidos chamando o método dataVenda() da classe Dao.
	 * 
	 * @return rd.dataVenda()
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<ModelTabelaLivro> dataVenda() throws ClassNotFoundException, SQLException {

		RelatorioDao rd = new RelatorioDao();

		return rd.dataVenda();
	}

	/**
	 * Método que mostra o melhor dia da semana para vendas chamando o método melhorDiaSemana() da classe Dao.
	 * 
	 * @return rd.melhorDiaSemana()
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int melhorDiaSemana() throws ClassNotFoundException, SQLException {

		RelatorioDao rd = new RelatorioDao();

		return rd.melhorDiaSemana();

	}

}
