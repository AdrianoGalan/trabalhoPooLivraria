package control;

import java.sql.SQLException;

import DAO.AutorDao;
import entity.Autor;
import javafx.collections.ObservableList;

/**
 * Classe controle do Autor
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControleAutor {

	/**
	 * M�todo que adiciona autor chamando o m�todo insereAutor da classe Dao 
	 * 
	 * @param a
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addAutor(Autor a) throws ClassNotFoundException, SQLException {
		AutorDao ad = new AutorDao();

		try {
			ad.insereAutor(a);
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
			System.out.println("1");
		}
	}

	/**
	 * M�todo que verifica se tem um nome duplicado no Banco de dados chamando o m�todo verificaDuplicNome da classe Dao
	 * 
	 * @param nome
	 * @return ad.verificaDuplicNome(nome)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean verificaDuplicata(String nome) throws SQLException, ClassNotFoundException {
		AutorDao ad = new AutorDao();
		return ad.verificaDuplicNome(nome);
	}

	/**
	 * M�todo que busca autores chamando o m�todo alteraAutor da classe Dao 
	 * 
	 * @param nome
	 * @return ad.pesquisarAutores(nome)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Autor> buscaClienteNome(String nome) throws ClassNotFoundException, SQLException {

		AutorDao ad = new AutorDao();

		return ad.pesquisarAutores(nome);

	}

	/**
	 * M�todo que altera autor chamando o m�todo alteraAutor da Classe Dao
	 * 
	 * @param a
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarAutor(Autor a) throws ClassNotFoundException, SQLException {
		AutorDao ad = new AutorDao();
		ad.alteraAutor(a);

	}

}
