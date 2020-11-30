package entity;

/**
 * Classe concreta que representa Autor
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class Autor {
	
	/** Propriedade idAutor. */
	private int idAutor;
	
	/** Propriedade nome. */
	private String nome;
	
	/** Propriedade nacionalidade. */
	private String nacionalidade;
	
	/** Construtor padrão */
	public Autor() {
		
	}

	/**
	 * Recupera a propriedade idAutor.
	 * 
	 * @return id do autor
	 */
	public int getIdAutor() {
		return idAutor;
	}

	/**
	 * Configura a propriedade idAutor.
	 * 
	 * @param idAutor
	 * 				Id Informado.
	 */
	
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	
	/**
	 * Recupera a propriedade nome.
	 * 
	 * @return nome do autor
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Configura a propriedade nome.
	 * 
	 * @param nome
	 * 				Nome Informado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Recupera a propriedade nacionalidade.
	 * 
	 * @return a nacionalidade do autor
	 */

	public String getNacionalidade() {
		return nacionalidade;
	}

	/**
	 * Configura a propriedade nacionalidade.
	 * 
	 * @param nacionalidade
	 * 				nacionalidade Informado.
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	/**
	 * Método toString
	 */
	@Override
	public String toString() {
		return "[idAutor=" + idAutor + ", nome=" + nome + ", nacionalidade=" + nacionalidade + "]";
	}
	
	

}
