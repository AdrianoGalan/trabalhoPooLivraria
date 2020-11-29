package tabelaModel;

public class ModelItensVenda {

	private int idLivro;
	private int iten;
	private String titulo;
	private String Isbn;
	private String generoLivro;
	private int estoqueLivro;
	private int qtsVenda;
	private double preco;

	public int getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	public int getIten() {
		return iten;
	}

	public void setIten(int iten) {
		this.iten = iten;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return Isbn;
	}

	public void setIsbn(String isbn) {
		Isbn = isbn;
	}

	public String getGeneroLivro() {
		return generoLivro;
	}

	public void setGeneroLivro(String generoLivro) {
		this.generoLivro = generoLivro;
	}

	public int getEstoqueLivro() {
		return estoqueLivro;
	}

	public void setEstoqueLivro(int estoqueLivro) {
		this.estoqueLivro = estoqueLivro;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQtsVenda() {
		return qtsVenda;
	}

	public void setQtsVenda(int qtsVenda) {
		this.qtsVenda = qtsVenda;
	}

}
