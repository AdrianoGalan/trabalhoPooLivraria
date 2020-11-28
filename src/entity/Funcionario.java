package entity;

import java.util.Date;
import java.util.GregorianCalendar;

public class Funcionario extends Pessoa {

	private int idFuncionario;
	private String cargo;
	private String matricula;
	private Date dataAdmissao;
	private int fkPessoaFuncionario;
	 
	public Funcionario() {
		super();
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date date) {
		this.dataAdmissao = date;
	}
	
	

	public int getFkPessoaFuncionario() {
		return fkPessoaFuncionario;
	}

	public void setFkPessoaFuncionario(int fkPessoaFuncionario) {
		this.fkPessoaFuncionario = fkPessoaFuncionario;
	}

	@Override
	public String toString() {
		return "[idFuncionario=" + idFuncionario + ", cargo=" + cargo + ", matricula=" + matricula
				+ ", dataAsmissao=" + dataAdmissao + "]";
	}
	
	
	
}
