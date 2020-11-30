package entity;

import java.util.ArrayList;

/**
 * Classe concreta que representa Venda
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class Venda {

	/** Propriedade idVenda. */
	private int idVenda;
	
	/** ArrayList idLivros. */
	private ArrayList<Integer> idLivros;

	/**
	 * Recupera a propriedade idVenda.
	 * 
	 * @return id da venda
	 */
	public int getIdVenda() {
		return idVenda;
	}

	/**
	 * Configura a propriedade idVenda.
	 * 
	 * @param idVenda
	 * 				id da venda informado.
	 */
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	/**
	 * Recupera o ArrayList idLivros.
	 * 
	 * @return id dos livros
	 */
	public ArrayList<Integer> getIdLivros() {
		return idLivros;
	}

	/**
	 * Configura o ArrayList idLivros.
	 * 
	 * @param idLivros
	 * 				id dos livros informado.
	 */
	public void setIdLivros(ArrayList<Integer> idLivros) {
		this.idLivros = idLivros;
	}

}
