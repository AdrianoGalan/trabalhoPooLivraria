package entity;

import java.util.GregorianCalendar;

public class Cliente extends Pessoa {

	private int idCliente;
	private GregorianCalendar dataCadastro;
	private int fkPessoaCliente;

	public Cliente() {

	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public GregorianCalendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(GregorianCalendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getFkPessoaCliente() {
		return fkPessoaCliente;
	}

	public void setFkPessoaCliente(int fkPessoaCliente) {
		this.fkPessoaCliente = fkPessoaCliente;
	}

	@Override
	public String toString() {
		return "[idCliente=" + idCliente + ", dataCadastro=" + dataCadastro + ", fkPessoaCliente=" + fkPessoaCliente
				+ "]";
	}

}
