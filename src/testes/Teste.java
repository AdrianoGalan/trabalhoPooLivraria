package testes;

import java.sql.SQLException;

import DAO.UsuarioDao;
import entity.Usuario;

public class Teste {

	public static void main(String[] args) {

		try {

			UsuarioDao uDao = new UsuarioDao();

			uDao.atualizarSenha(5, "senha");

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
