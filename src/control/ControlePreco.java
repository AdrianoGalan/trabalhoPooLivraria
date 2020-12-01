package control;

import java.sql.SQLException;

import DAO.PrecoDao;
import entity.Preco;

/**
 * Classe controle de Preco.
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControlePreco {
	
	/**
	 * M�todo que busca preco do livro pelo id chamando o m�todo buscaPrecoId() da classe Dao.
	 * 
	 * @param id
	 * @return pd.buscaPrecoId(id)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Preco buscaPrecoId(int id) throws ClassNotFoundException, SQLException {
		
		PrecoDao pd = new PrecoDao();
		
		return pd.buscaPrecoId(id);
		
	}
	
	/**
	 * M�todo que adiciona preco chamando o m�todo inserirPreco() da classe Dao.
	 * 
	 * @param p
	 * @return pd.inserirPreco(p)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int addPreco(Preco p) throws ClassNotFoundException, SQLException {
		
		PrecoDao pd = new PrecoDao();
		
		return pd.inserirPreco(p);
		
	}
	

}
