package entity;

import java.util.Date;

/**
 * Classe concreta que representa Cliente e extends a classe Pessoa
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class Cliente extends Pessoa {

	/** Propriedade idCliente. */
	private int idCliente;
	
	/** Propriedade dataCadastro. */
	private Date dataCadastro;
	
	/** Propriedade fkPessoaCliente. */
	private int fkPessoaCliente;

	/** Construtor padrao */
	public Cliente() {

	}

	/**
	 * Recupera a propriedade idCliente.
	 * 
	 * @return id do cliente
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * Configura a propriedade idCliente.
	 * 
	 * @param idCliente
	 * 				Id Informado.
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Recupera a propriedade dataCadastro.
	 * 
	 * @return data de cadastro do cliente
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Configura a propriedade dataCadastro.
	 * 
	 * @param dataCadastro
	 * 				Data cadastro Informado.
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Recupera a propriedade fkPessoaCliente.
	 * 
	 * @return fkPessoaCliente
	 */
	public int getFkPessoaCliente() {
		return fkPessoaCliente;
	}

	/**
	 * Configura a propriedade fkPessoaCliente.
	 * 
	 * @param fkPessoaCliente
	 * 				fkPessoaCliente Informado.
	 */
	public void setFkPessoaCliente(int fkPessoaCliente) {
		this.fkPessoaCliente = fkPessoaCliente;
	}

	/**
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return super.getNome();
	}

}
