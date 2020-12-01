package entity;

/**
 * Classe concreta que representa Endereco
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class Endereco {

	/** Propriedade idEndereco. */
	private int idEndereco;
	
	/** Propriedade rua. */
	private String rua;
	
	/** Propriedade numero. */
	private int numero;
	
	/** Propriedade bairro. */
	private String bairro;
	
	/** Propriedade cidade. */
	private String cidade;
	
	/** Propriedade estado. */
	private String estado;
	
	/** Propriedade complemento. */
	private String complemento;
	
	/** Propriedade cep. */
	private String cep;

	/** Construtor padrao */
	public Endereco() {

	}

	/**
	 * Recupera a propriedade idEndereco.
	 * 
	 * @return id do Endereco
	 */
	public int getIdEndereco() {
		return idEndereco;
	}

	/**
	 * Configura a propriedade IdEndereco.
	 * 
	 * @param IdEndereco
	 * 				Id do endereco Informado.
	 */
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	/**
	 * Recupera a propriedade Rua.
	 * 
	 * @return rua
	 */
	public String getRua() {
		return rua;
	}

	/**
	 * Configura a propriedade rua.
	 * 
	 * @param rua
	 * 				rua Informada.
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}

	/**
	 * Recupera a propriedade numero.
	 * 
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Configura a propriedade numero.
	 * 
	 * @param numero
	 * 				numero Informado.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Recupera a propriedade bairro.
	 * 
	 * @return bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Configura a propriedade bairro.
	 * 
	 * @param bairro
	 * 				bairro Informado.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * Recupera a propriedade cidade.
	 * 
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Configura a propriedade cidade.
	 * 
	 * @param cidade
	 * 				cidade Informado.
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Recupera a propriedade estado.
	 * 
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Configura a propriedade estado.
	 * 
	 * @param estado
	 * 				estado Informado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Recupera a propriedade complemento.
	 * 
	 * @return complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Configura a propriedade complemento.
	 * 
	 * @param complemento
	 * 				complemento Informado.
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Recupera a propriedade cep.
	 * 
	 * @return cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Configura a propriedade cep.
	 * 
	 * @param cep
	 * 				cep Informado.
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "[idEndereco=" + idEndereco + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade="
				+ cidade + ", estado=" + estado + ", complemento=" + complemento + ", cep=" + cep + "]";
	}

}
