package entity;

import java.util.Date;

/**
 * Classe concreta que representa Pessoa
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class Pessoa {

	/** Propriedade idPessoa. */
	private int idPessoa;
	
	/** Propriedade nome. */
	private String nome;
	
	/** Propriedade cpf. */
	private String cpf;
	
	/** Propriedade email. */
	private String email;
	
	/** Propriedade dataNascimento. */
	private Date dataNascimento;
	
	/** Propriedade fkEdetecoPessoa. */
	private int fkEdetecoPessoa;

	/** Construtor */
	public Pessoa() {
		super();
	}

	/**
	 * Recupera a propriedade idPessoa.
	 * 
	 * @return id da pessoa
	 */
	public int getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Configura a propriedade idPessoa.
	 * 
	 * @param idPessoa
	 * 				id Pessoa informado.
	 */
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera a propriedade nome.
	 * 
	 * @return nome da pessoa
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Configura a propriedade nome.
	 * 
	 * @param nome
	 * 				nome da pessoa informado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Recupera a propriedade cpf.
	 * 
	 * @return cpf da pessoa
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Configura a propriedade cpf.
	 * 
	 * @param cpf
	 * 				cpf da pessoa informado.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Recupera a propriedade email.
	 * 
	 * @return email da pessoa
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Configura a propriedade email.
	 * 
	 * @param email
	 * 				email da pessoa informado.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Recupera a propriedade dataNascimento.
	 * 
	 * @return data nascimento da pessoa
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Configura a propriedade dataNascimento.
	 * 
	 * @param dataNascimento
	 * 				data de nascimento informada.
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Recupera a propriedade fkEdetecoPessoa.
	 * 
	 * @return fkEdetecoPessoa
	 */
	public int getFkEdetecoPessoa() {
		return fkEdetecoPessoa;
	}

	/**
	 * Configura a propriedade fkEdetecoPessoa.
	 * 
	 * @param fkEdetecoPessoa
	 * 				fkEdetecoPessoa informado.
	 */
	public void setFkEdetecoPessoa(int fkEdetecoPessoa) {
		this.fkEdetecoPessoa = fkEdetecoPessoa;
	}

	/**
	 * Método toString
	 */
	@Override
	public String toString() {
		return "[idPessoa=" + idPessoa + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", fkEdetecoPessoa=" + fkEdetecoPessoa + "]";
	}

}
