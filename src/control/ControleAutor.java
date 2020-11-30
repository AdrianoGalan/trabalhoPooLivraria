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
	 * Método que adiciona autor chamando o método insereAutor da classe Dao 
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
	 * Método que verifica se tem um nome duplicado no Banco de dados chamando o método verificaDuplicNome da classe Dao
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
	 * Método que busca autores chamando o método alteraAutor da classe Dao 
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
	 * Método que altera autor chamando o método alteraAutor da Classe Dao
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
