package tabelaModel;

/**
 * Classe ModelItensVenda - tabela venda 
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class ModelItensVenda {

	/** Propriedade idLivro */
	private int idLivro;
	
	/** Propriedade iten */
	private int iten;
	
	/** Propriedade titulo*/
	private String titulo;
	
	/** Propriedade isbn */
	private String Isbn;
	
	/** Propriedade generoLivro */
	private String generoLivro;
	
	/** Propriedade estoqueLivro */
	private int estoqueLivro;
	
	/** Propriedade qtsVenda */
	private int qtsVenda;
	
	/** Propriedade preco */
	private double preco;

	/**
	 * Recupera a propriedade idLivro
	 * 
	 * @return id do livro
	 */
	public int getIdLivro() {
		return idLivro;
	}

	/**
	 * Configura a propriedade idLivro.
	 * 
	 * @param idLivro
	 * 				Id Informado
	 */
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	/**
	 * Recupera a propriedade iten.
	 * 
	 * @return item
	 */
	public int getIten() {
		return iten;
	}

	/**
	 * Configura a propriedade iten.
	 * 
	 * @param iten
	 * 				item informado.
	 */
	public void setIten(int iten) {
		this.iten = iten;
	}

	/**
	 * Recupera a propriedade titulo.
	 * 
	 * @return titulo do livro
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Configura a propriedade titulo.
	 * 
	 * @param titulo
	 * 				titulo Informado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Recupera a propriedade isbn.
	 * 
	 * @return isbn do livro
	 */
	public String getIsbn() {
		return Isbn;
	}

	/**
	 * Configura a propriedade isbn
	 * @param isbn
	 * 				isbn informado.
	 */
	public void setIsbn(String isbn) {
		Isbn = isbn;
	}

	/**
	 * Recupera a propriedade generoLivro
	 * 
	 * @return genero do Livro
	 */
	public String getGeneroLivro() {
		return generoLivro;
	}

	/**
	 * Configura a propriedade generoLivro.
	 * 
	 * @param generoLivro
	 * 						genero informado.
	 */
	public void setGeneroLivro(String generoLivro) {
		this.generoLivro = generoLivro;
	}

	/**
	 * Recupera a propriedade estoqueLivro.
	 * 
	 * @return estoque de Livro
	 */
	public int getEstoqueLivro() {
		return estoqueLivro;
	}

	/**
	 * Configura a propriedade estoqueLivro.
	 * 
	 * @param estoqueLivro
	 * 						estoque informado.
	 */
	public void setEstoqueLivro(int estoqueLivro) {
		this.estoqueLivro = estoqueLivro;
	}

	/**
	 * Recupera a propriedade preco.
	 * 
	 * @return preco do livro
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Configura a propriedade preco.
	 * 
	 * @param preco
	 * 				preco informado.
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * Recupera a propriedade qtsVenda.
	 * 
	 * @return quantidade de venda
	 */
	public int getQtsVenda() {
		return qtsVenda;
	}

	/**
	 * Configura a propriedade qtsVenda
	 * 
	 * @param qtsVenda
	 * 					quantidade informada.
	 */
	public void setQtsVenda(int qtsVenda) {
		this.qtsVenda = qtsVenda;
	}

}
