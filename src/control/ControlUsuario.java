package control;

import java.sql.SQLException;

import DAO.FuncionarioDao;
import DAO.UsuarioDao;
import entity.Funcionario;
import entity.Usuario;
import javafx.collections.ObservableList;
import util.Mensagens;

/**
 * Classe controle do usuario
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class ControlUsuario {
	/** Propriedade ObservableList listaUsuarios */
	private ObservableList<Usuario> listaUsuarios;
	
	/** Propriedade ObservableList listaFunc */
	private ObservableList<Funcionario> listaFunc;
	
	/** Propriedade FuncionarioDao fDao */
	private FuncionarioDao fDao;
	

	/**
	 * Metodo que busca usuario no sistema chamando o metodo buscaUsuarioUsuario() 
	 * da classe Dao e faz login caso ele seja encontrado  
	 * 
	 * @param u
	 * @return usuario or null
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Usuario logarNoSistema(Usuario u) throws ClassNotFoundException, SQLException {
		
		
		UsuarioDao uDao = new UsuarioDao();
		Usuario uBanco = uDao.buscaUsuarioUsuario(u.getLogin());
		
		if(u.getSenha().equals(uBanco.getSenha())) {
			
			return uBanco;
		}
		
		Mensagens.erro("Erro login", "Usuario ou senha invalido", "Digite usuario e senha valido");
		return null;
	}
	
	/**
	 * Metodo que cadastra dados do usuario chamando o metodo insereUsuario() da classe Dao.
	 * 
	 * @param f
	 * @param login
	 * @param senha
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrarUsuario(Funcionario f, String login,String senha) throws ClassNotFoundException, SQLException {
		UsuarioDao uDao = new UsuarioDao();
		Usuario u = new Usuario();
		u.setLogin(login);
		u.setSenha(senha);
		u.setfKFuncionario(f.getIdFuncionario());
		uDao.insereUsuario(u);
	}
	
	/**
	 * Metodo que altera usuario chamando o metodo alteraUsuario() da classe Dao.
	 * 
	 * @param f
	 * @param login
	 * @param senha
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarUsuario(Funcionario f, String login,String senha) throws ClassNotFoundException, SQLException {
		UsuarioDao uDao = new UsuarioDao();
		Usuario u = new Usuario();
		u.setLogin(login);
		u.setSenha(senha);
		u.setfKFuncionario(f.getIdFuncionario());
		uDao.alteraUsuario(u);
	}
	
	/**
	 * Metodo que exclui usuario chamando o metodo deletarUsuario() da classe Dao.
	 * 
	 * @param idUsu
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void deletarUsuario(int idUsu) throws SQLException, ClassNotFoundException {
		UsuarioDao uDao = new UsuarioDao();
		uDao.deletarUsuario(idUsu);
	}
	
	
	/**
	 * Metodo que busca a lista de usuarios chamando o metodo buscaListaUsuarios() da classe Dao.
	 * 
	 * @return listaUsuarios
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Usuario> procurarUsuarios() throws ClassNotFoundException, SQLException {
		UsuarioDao usuDao = new UsuarioDao();
		listaUsuarios = usuDao.buscaListaUsuarios();
		return listaUsuarios;
		
	}

	/**
	 * Metodo que busca a lista de funcionarios chamando o metodo getListaFuncionario() da classe Dao. 
	 * 
	 * @return listaFunc
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Funcionario> getListaFuncionarios() throws ClassNotFoundException, SQLException {
		fDao = new FuncionarioDao();
		listaFunc = fDao.getListaFuncionario();
		return listaFunc;
	}

	/**
	 * Metodo que retorna a lista de usuarios
	 * 
	 * @return listaUsuarios
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ObservableList<Usuario> getListaUsuarios() throws ClassNotFoundException, SQLException {
		fDao = new FuncionarioDao();
		return listaUsuarios;
	}
	
	/**
	 * Metodo que verifica se o login existe chamando o metodo buscaUsuario() da classe Dao.
	 * 
	 * @param login
	 * @returnusuDao.buscaUsuario(login)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean verificaExistenciaLogin(String login) throws ClassNotFoundException, SQLException {
		UsuarioDao usuDao = new UsuarioDao();
		return usuDao.buscaUsuario(login);
	}
	
	

}
