package entity;

import java.util.Date;
import java.util.GregorianCalendar;

public class Cliente extends Pessoa {

	private int idCliente;
	private Date dataCadastro;
	private int fkPessoaCliente;

	public Cliente() {

	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
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
		return super.getNome();
	}

}
