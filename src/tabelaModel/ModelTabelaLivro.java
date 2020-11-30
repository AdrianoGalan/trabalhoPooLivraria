package tabelaModel;

/**
 * Classe tabela livro
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */

public class ModelTabelaLivro {

	private int idLivro;
	private String preco;
	private String titulo;
	private String autor;
	private String isbn;
	private String genero;
	private String edicao;
	private String ano;
	private int qtsEstoque;
	private String idioma;
	private String descricao;

	public int getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public int getQtsEstoque() {
		return qtsEstoque;
	}

	public void setQtsEstoque(int qtsEstoque) {
		this.qtsEstoque = qtsEstoque;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Método toString
	 */
	
	@Override
	public String toString() {
		return "ModelTabelaLivro [preco=" + preco + ", titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn
				+ ", genero=" + genero + ", edicao=" + edicao + ", ano=" + ano + ", qtsEstoque=" + qtsEstoque
				+ ", idioma=" + idioma + ", descricao=" + descricao + "]";
	}

}
