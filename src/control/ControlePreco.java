package control;

import java.sql.SQLException;

import DAO.PrecoDao;
import entity.Preco;

public class ControlePreco {
	
	public Preco buscaPrecoId(int id) throws ClassNotFoundException, SQLException {
		
		PrecoDao pd = new PrecoDao();
		
		return pd.buscaPrecoId(id);
		
	}
	
	public int addPreco(Preco p) throws ClassNotFoundException, SQLException {
		
		PrecoDao pd = new PrecoDao();
		
		return pd.inserirPreco(p);
		
	}
	

}
