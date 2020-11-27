package control;

import java.sql.SQLException;

import DAO.FuncionarioDao;
import DAO.UsuarioDao;
import entity.Funcionario;
import entity.Usuario;
import javafx.collections.ObservableList;
import util.Mensagens;

public class ControlUsuario {
	private ObservableList<Usuario> listaUsuarios;
	private ObservableList<Funcionario> listaFunc;
	private FuncionarioDao fDao;
	

	

	public Usuario logarNoSistema(Usuario u) throws ClassNotFoundException, SQLException {
		
		
		UsuarioDao uDao = new UsuarioDao();
		
		Usuario uBanco = uDao.buscaUsuarioUsuario(u.getLogin());
		
		if(u.getSenha().equals(uBanco.getSenha())) {
			
			return uBanco;
		}
		
		Mensagens.erro("Erro login", "Usuario ou senha invalido", "Digite usuari e senha valido");
		return null;
	}
	
	public void cadastrarUsuario(Funcionario f, String login,String senha) throws ClassNotFoundException, SQLException {
		UsuarioDao uDao = new UsuarioDao();
		Usuario u = new Usuario();
		u.setLogin(login);
		u.setSenha(senha);
		u.setfKFuncionario(f.getIdFuncionario());
		uDao.insereUsuario(u);
	}
	
	public void alterarUsuario(Funcionario f, String login,String senha) throws ClassNotFoundException, SQLException {
		UsuarioDao uDao = new UsuarioDao();
		Usuario u = new Usuario();
		u.setLogin(login);
		u.setSenha(senha);
		u.setfKFuncionario(f.getIdFuncionario());
		uDao.alteraUsuario(u);
	}
	
	public void deletarUsuario(int idUsu) throws SQLException, ClassNotFoundException {
		UsuarioDao uDao = new UsuarioDao();
		uDao.deletarUsuario(idUsu);
	}
	
	
	
	public ObservableList<Usuario> procurarUsuarios() throws ClassNotFoundException, SQLException {
		UsuarioDao usuDao = new UsuarioDao();
		listaUsuarios = usuDao.buscaListaUsuarios();
		return listaUsuarios;
		
	}

	public ObservableList<Funcionario> getListaFuncionarios() throws ClassNotFoundException, SQLException {
		fDao = new FuncionarioDao();
		listaFunc = fDao.getListaFuncionario();
		return listaFunc;
	}

	public ObservableList<Usuario> getListaUsuarios() throws ClassNotFoundException, SQLException {
		fDao = new FuncionarioDao();
		return listaUsuarios;
	}
	
	
	
	

}
