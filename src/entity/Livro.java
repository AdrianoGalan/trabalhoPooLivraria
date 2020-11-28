package entity;

public class Livro {

	private int idLivro;
	private String titulo;
	private String isbn;
	private String genero;
	private String edicao;
	private String ano;
	private int precoAtual;
	private int qtsEstoque;
	private String idioma;
	private String descricao;

	public Livro() {

	}

	public int getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public int getPrecoAtual() {
		return precoAtual;
	}

	public void setPrecoAtual(int precoAtual) {
		this.precoAtual = precoAtual;
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

	@Override
	public String toString() {
		return titulo;
	}

}
