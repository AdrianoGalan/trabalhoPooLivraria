package control;

import java.sql.SQLException;

import DAO.UsuarioDao;
import entity.Usuario;
import javafx.collections.ObservableList;
import util.Mensagens;

public class ControlUsuario {
	private ObservableList<Usuario> lista;
	
	public Usuario logarNoSistema(Usuario u) throws ClassNotFoundException, SQLException {
		
		
		UsuarioDao uDao = new UsuarioDao();
		Usuario uBanco = uDao.buscaUsuarioUsuario(u.getLogin());
		
		if(u.getSenha().equals(uBanco.getSenha())) {
			
			return uBanco;
		}
		
		Mensagens.erro("Erro login", "Usuario ou senha invalido", "Digite usuari e senha valido");
		return null;
	}
	
	public ObservableList<Usuario> procurarUsuarios() throws ClassNotFoundException, SQLException {
		UsuarioDao usuDao = new UsuarioDao();
		lista = usuDao.buscaListaUsuarios();
		return lista;
		
	}

	public ObservableList<Usuario> getLista() {
		return lista;
	}
	
	

}
