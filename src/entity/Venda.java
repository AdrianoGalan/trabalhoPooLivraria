package entity;

import java.util.ArrayList;

public class Venda {

	private int idVenda;
	private ArrayList<Integer> idLivros;

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public ArrayList<Integer> getIdLivros() {
		return idLivros;
	}

	public void setIdLivros(ArrayList<Integer> idLivros) {
		this.idLivros = idLivros;
	}

}
