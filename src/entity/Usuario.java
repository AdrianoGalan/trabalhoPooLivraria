package entity;

/**
 * Classe concreta que representa Usuario e extends a classe Funcionario
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class Usuario extends Funcionario {

	/** Propriedade idUsuario. */
	private int idUsuario;
	
	/** Propriedade login. */
	private String login;
	
	/** Propriedade senha. */
	private String senha;
	
	/** Propriedade fKFuncionario. */
	private int fKFuncionario;

	/** Construtor padrão */
	public Usuario() {

	}

	/**
	 * Construtor
	 * @param login
	 * @param senha
	 */
	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	/**
	 * Inicializando propriedade
	 * 
	 * @param login
	 * @param senha
	 * @param fKFuncionario
	 */
	public Usuario(String login, String senha, int fKFuncionario) {
		super();
		this.login = login;
		this.senha = senha;
		this.fKFuncionario = fKFuncionario;
	}

	/**
	 * Recupera a propriedade idUsuario.
	 * 
	 * @return id do usuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Configura a propriedade idUsuario.
	 * 
	 * @param idUsuario
	 * 				id do Usuario informado.
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Recupera a propriedade login.
	 * 
	 * @return login do usuario
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Configura a propriedade login.
	 * 
	 * @param login
	 * 				login do usuario informado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Recupera a propriedade senha.
	 * 
	 * @return senha do usuario
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Configura a propriedade senha.
	 * 
	 * @param senha
	 * 				senha do usuario informado.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Recupera a propriedade fKFuncionario.
	 * 
	 * @return fKFuncionario
	 */
	public int getfKFuncionario() {
		return fKFuncionario;
	}

	/**
	 * Configura a propriedade fKFuncionario.
	 * 
	 * @param fKFuncionario
	 * 				fKFuncionario informado.
	 */
	public void setfKFuncionario(int fKFuncionario) {
		this.fKFuncionario = fKFuncionario;
	}

	/**
	 * Método toString
	 */
	@Override
	public String toString() {
		return "[idUsuario=" + idUsuario + ", login=" + login + ", senha=" + senha + ", fKFuncionario=" + fKFuncionario + "]";
	}

}
