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
	 * M�todo que busca os livros mais vendidos chamando o m�todo livroMaisVendido() da classe Dao.
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
	 * M�todo que busca os livros menos vendidos chamando o m�todo livroMenosVendido() da classe Dao. 
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
	 * M�todo que busca livros com estoque menor que 5 chamando o m�todo livroEstoqueBaixo() da classe Dao.
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
	 * M�todo que busca a data dos ultimos livros vendidos chamando o m�todo dataVenda() da classe Dao.
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
	 * M�todo que mostra o melhor dia da semana para vendas chamando o m�todo melhorDiaSemana() da classe Dao.
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
