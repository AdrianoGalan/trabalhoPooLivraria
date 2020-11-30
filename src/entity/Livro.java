package entity;

/**
 * Classe concreta que representa Livro
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class Livro {

	/** Propriedade idLivro. */
	private int idLivro;
	
	/** Propriedade titulo. */
	private String titulo;
	
	/** Propriedade isbn. */
	private String isbn;
	
	/** Propriedade genero. */
	private String genero;
	
	/** Propriedade edicao. */
	private String edicao;
	
	/** Propriedade ano. */
	private String ano;
	
	/** Propriedade precoAtual. */
	private int precoAtual;
	
	/** Propriedade qtsEstoque. */
	private int qtsEstoque;
	
	/** Propriedade idioma. */
	private String idioma;
	
	/** Propriedade descricao. */
	private String descricao;

	/** Construtor padrão */
	public Livro() {

	}

	/**
	 * Recupera a propriedade idLivro.
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
	 * 				id do livro informado.
	 */
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
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
	 * 				titulo do livro informado.
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
		return isbn;
	}

	/**
	 * Configura a propriedade isbn.
	 * 
	 * @param isbn
	 * 				isbn do livro informado.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Recupera a propriedade genero.
	 * 
	 * @return genero do livro
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Configura a propriedade genero.
	 * 
	 * @param genero
	 * 				genero do livro informado.
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Recupera a propriedade edicao.
	 * 
	 * @return edicao do livro
	 */
	public String getEdicao() {
		return edicao;
	}

	/**
	 * Configura a propriedade edicao.
	 * 
	 * @param edicao
	 * 				edicao do livro informado.
	 */
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	/**
	 * Recupera a propriedade ano.
	 * 
	 * @return ano do livro
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * Configura a propriedade ano.
	 * 
	 * @param ano
	 * 				ano do livro informado.
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * Recupera a propriedade precoAtual.
	 * 
	 * @return preco atual do livro
	 */
	public int getPrecoAtual() {
		return precoAtual;
	}

	/**
	 * Configura a propriedade precoAtual.
	 * 
	 * @param precoAtual
	 * 				preco atual do livro informado.
	 */
	public void setPrecoAtual(int precoAtual) {
		this.precoAtual = precoAtual;
	}

	/**
	 * Recupera a propriedade qtsEstoque.
	 * 
	 * @return quantos livros tem no estoque 
	 */
	public int getQtsEstoque() {
		return qtsEstoque;
	}

	/**
	 * Configura a propriedade qtsEstoque.
	 * 
	 * @param qtsEstoque
	 * 				quantidade de livros no estoque informado.
	 */
	public void setQtsEstoque(int qtsEstoque) {
		this.qtsEstoque = qtsEstoque;
	}

	/**
	 * Recupera a propriedade idioma.
	 * 
	 * @return idioma do livro
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * Configura a propriedade idioma.
	 * 
	 * @param idioma
	 * 				idioma do livro informado.
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * Recupera a propriedade descricao.
	 * 
	 * @return descrição do livro
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Configura a propriedade descricao.
	 * 
	 * @param descricao
	 * 				descrição do livro informado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Método toString
	 */
	@Override
	public String toString() {
		return titulo;
	}

}
