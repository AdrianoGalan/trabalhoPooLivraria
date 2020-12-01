package entity;

import java.util.Date;

/**
 * Classe concreta que representa Preco
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class Preco {

	/** Propriedade idPreco. */
	private int idPreco;
	
	/** Propriedade dataPreco. */
	private Date dataPreco;
	
	/** Propriedade valor. */
	private double valor;
	
	/** Propriedade fkLivroPreco. */
	private int fkLivroPreco;

	/**
	 * Recupera a propriedade idPreco.
	 * 
	 * @return id do preco
	 */
	public int getIdPreco() {
		return idPreco;
	}

	/**
	 * Configura a propriedade idPreco.
	 * 
	 * @param idPreco
	 * 				Id Preco Informado.
	 */
	public void setIdPreco(int idPreco) {
		this.idPreco = idPreco;
	}

	/**
	 * Recupera a propriedade dataPreco.
	 * 
	 * @return data Preco do preco
	 */
	public Date getDataPreco() {
		return dataPreco;
	}

	/**
	 * Configura a propriedade dataPreco.
	 * 
	 * @param dataPreco
	 * 				data Preco Informado.
	 */
	public void setDataPreco(Date dataPreco) {
		this.dataPreco = dataPreco;
	}

	/**
	 * Recupera a propriedade valor.
	 * 
	 * @return valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Configura a propriedade valor.
	 * 
	 * @param valor
	 * 				valor Informado.
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * Recupera a propriedade fkLivroPreco.
	 * 
	 * @return fkLivroPreco
	 */
	public int getFkLivroPreco() {
		return fkLivroPreco;
	}

	/**
	 * Configura a propriedade fkLivroPreco.
	 * 
	 * @param fkLivroPreco
	 * 				fkLivroPreco Informado.
	 */
	public void setFkLivroPreco(int fkLivroPreco) {
		this.fkLivroPreco = fkLivroPreco;
	}

	/**
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "Preco [idPreco=" + idPreco + ", dataPreco=" + dataPreco + ", valor=" + valor + ", fkLivroPreco="
				+ fkLivroPreco + "]";
	}

}
