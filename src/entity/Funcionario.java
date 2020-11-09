package entity;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Funcionario extends Pessoa {

	private int idFuncionario;
	private String cargo;
	private String matricula;
	private Date dataAsmissao;
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

	public Date getDataAsmissao() {
		return dataAsmissao;
	}

	public void setDataAsmissao(Date dataAsmissao) {
		this.dataAsmissao = dataAsmissao;
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
				+ ", dataAsmissao=" + dataAsmissao + "]";
	}
	
	
	
}