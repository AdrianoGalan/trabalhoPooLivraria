package entity;

import java.util.Date;

/**
 * Classe concreta que representa Funcionario e extends a classe Pessoa
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class Funcionario extends Pessoa {

	/** Propriedade idFuncionario. */
	private int idFuncionario;
	
	/** Propriedade cargo. */
	private String cargo;
	
	/** Propriedade matricula. */
	private String matricula;
	
	/** Propriedade dataAdmissao. */
	private Date dataAdmissao;
	
	/** Propriedade fkPessoaFuncionario. */
	private int fkPessoaFuncionario;
	 
	/** Construtor */
	public Funcionario() {
		super();
	}

	/**
	 * Recupera a propriedade idFuncionario.
	 * 
	 * @return id do Funcionário
	 */
	public int getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * Configura a propriedade idFuncionario.
	 * 
	 * @param idFuncionario
	 * 				Id Informado.
	 */
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * Recupera a propriedade cargo.
	 * 
	 * @return cargo do funcionario
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Configura a propriedade cargo.
	 * 
	 * @param cargo
	 * 				cargo Informado.
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Recupera a propriedade matricula.
	 * 
	 * @return matricula do funcionario
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Configura a propriedade matricula.
	 * 
	 * @param matricula
	 * 				matricula Informado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Recupera a propriedade dataAdmissao.
	 * 
	 * @return data de Admissao do funcionario
	 */
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * Configura a propriedade dataAdmissao.
	 * 
	 * @param date
	 * 				data de Admissao Informado.
	 */
	public void setDataAdmissao(Date date) {
		this.dataAdmissao = date;
	}
	
	
	/**
	 * Recupera a propriedade fkPessoaFuncionario.
	 * 
	 * @return fkPessoaFuncionario
	 */
	public int getFkPessoaFuncionario() {
		return fkPessoaFuncionario;
	}

	/**
	 * Configura a propriedade fkPessoaFuncionario.
	 * 
	 * @param fkPessoaFuncionario
	 * 				fkPessoaFuncionario Informado.
	 */
	public void setFkPessoaFuncionario(int fkPessoaFuncionario) {
		this.fkPessoaFuncionario = fkPessoaFuncionario;
	}

	/**
	 * Método toString
	 */
	@Override
	public String toString() {
		return "[idFuncionario=" + idFuncionario + ", cargo=" + cargo + ", matricula=" + matricula
				+ ", dataAsmissao=" + dataAdmissao + "]";
	}
	
	
	
}
