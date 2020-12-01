package entity;

/**
 * Classe concreta que representa Telefone 
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class Telefone {

	/** Propriedade idTelefone. */
	private int idTelefone;
	
	/** Propriedade tipo. */
	private String tipo;
	
	/** Propriedade ddd. */
	private String ddd;
	
	/** Propriedade numero. */
	private String numero;
	
	/** Propriedade fkPessoaTelefone. */
	private int fkPessoaTelefone;

	/** Construtor */
	public Telefone() {
		super();
	}

	/**
	 * Recupera a propriedade idTelefone.
	 * 
	 * @return id Telefone 
	 */
	public int getIdTelefone() {
		return idTelefone;
	}

	/**
	 * Configura a propriedade idTelefone.
	 * 
	 * @param idTelefone
	 * 				idTelefone informado.
	 */
	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	/**
	 * Recupera a propriedade tipo.
	 * 
	 * @return tipo de telefone
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Configura a propriedade tipo.
	 * 
	 * @param tipo
	 * 				tipo de telefone informado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Recupera a propriedade ddd.
	 * 
	 * @return ddd do telefone
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * Configura a propriedade ddd.
	 * 
	 * @param ddd
	 * 				ddd telefone informado.
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * Recupera a propriedade numero.
	 * 
	 * @return numero de telefone
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Configura a propriedade numero.
	 * 
	 * @param numero
	 * 				numero de telefone informado.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Recupera a propriedade fkPessoaTelefone.
	 * 
	 * @return fkPessoaTelefone
	 */
	public int getFkPessoaTelefone() {
		return fkPessoaTelefone;
	}

	/**
	 * Configura a propriedade fkPessoaTelefone.
	 * 
	 * @param fkPessoaTelefone
	 * 				fkPessoaTelefone informado.
	 */
	public void setFkPessoaTelefone(int fkPessoaTelefone) {
		this.fkPessoaTelefone = fkPessoaTelefone;
	}

	/**
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "[idTelefone=" + idTelefone + ", tipo=" + tipo + ", ddd=" + ddd + ", numero=" + numero
				+ ", fkPessoaTelefone=" + fkPessoaTelefone + "]";
	}

}
