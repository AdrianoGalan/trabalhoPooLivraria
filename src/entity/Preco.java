package entity;

import java.util.Date;

public class Preco {

	private int idPreco;
	private Date dataPreco;
	private double valor;
	private int fkLivroPreco;

	public int getIdPreco() {
		return idPreco;
	}

	public void setIdPreco(int idPreco) {
		this.idPreco = idPreco;
	}

	public Date getDataPreco() {
		return dataPreco;
	}

	public void setDataPreco(Date dataPreco) {
		this.dataPreco = dataPreco;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getFkLivroPreco() {
		return fkLivroPreco;
	}

	public void setFkLivroPreco(int fkLivroPreco) {
		this.fkLivroPreco = fkLivroPreco;
	}

	@Override
	public String toString() {
		return "Preco [idPreco=" + idPreco + ", dataPreco=" + dataPreco + ", valor=" + valor + ", fkLivroPreco="
				+ fkLivroPreco + "]";
	}

}
