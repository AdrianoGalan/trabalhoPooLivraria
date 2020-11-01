package entity;

public class Usuario {

	private int idUsuario;
	private String login;
	private String senha;
	private int fKFuncionario;

	public Usuario() {

	}

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Usuario(String login, String senha, int fKFuncionario) {
		super();
		this.login = login;
		this.senha = senha;
		this.fKFuncionario = fKFuncionario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getfKFuncionario() {
		return fKFuncionario;
	}

	public void setfKFuncionario(int fKFuncionario) {
		this.fKFuncionario = fKFuncionario;
	}

	@Override
	public String toString() {
		return "[idUsuario=" + idUsuario + ", login=" + login + ", senha=" + senha + ", fKFuncionario=" + fKFuncionario + "]";
	}

}
