package entity;

import java.util.Date;
import java.util.GregorianCalendar;

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String cpf;
	private String email;
	private Date dataNascimento;
	private int fkEdetecoPessoa;
	
	public Pessoa() {
		super();
	}

	
	

	public int getIdPessoa() {
		return idPessoa;
	}




	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getCpf() {
		return cpf;
	}




	public void setCpf(String cpf) {
		this.cpf = cpf;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public Date getDataNascimento() {
		return dataNascimento;
	}




	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}




	public int getFkEdetecoPessoa() {
		return fkEdetecoPessoa;
	}




	public void setFkEdetecoPessoa(int fkEdetecoPessoa) {
		this.fkEdetecoPessoa = fkEdetecoPessoa;
	}




	@Override
	public String toString() {
		return "[idPessoa=" + idPessoa + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email
				+ ", dataNascimento=" + dataNascimento + ", fkEdetecoPessoa=" + fkEdetecoPessoa + "]";
	}
	
	

}
