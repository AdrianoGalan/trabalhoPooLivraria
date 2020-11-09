package control;

import java.sql.SQLException;

import DAO.UsuarioDao;
import entity.Usuario;
import util.Mensagens;

public class ControlUsuario {
	
	public Usuario logarNoSistema(Usuario u) throws ClassNotFoundException, SQLException {
		
		
		UsuarioDao uDao = new UsuarioDao();
		Usuario uBanco = uDao.buscaUsuarioUsuario(u.getLogin());
		
		if(u.getSenha().equals(uBanco.getSenha())) {
			
			return uBanco;
		}
		
		Mensagens.erro("Erro login", "Usuario ou senha invalido", "Digite usuari e senha valido");
		return null;
	}

}
