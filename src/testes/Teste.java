package testes;

import java.sql.SQLException;

import DAO.UsuarioDao;
import entity.Usuario;

public class Teste {

	public static void main(String[] args) {

		try {

			UsuarioDao uDao = new UsuarioDao();

			Usuario usuario = uDao.buscaUsuarioUsuario("joão");

			if (usuario.getIdUsuario() == 0 || usuario == null) {
				System.out.println("usuario não existe");
			} else {

				System.out.println(usuario);
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
