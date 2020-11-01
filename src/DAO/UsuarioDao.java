package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Usuario;

public class UsuarioDao {

	private Connection c;

	public UsuarioDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();

		c = con.getConnection();

	}

	public void insereUsuario(Usuario u, int fKFuncionario) throws SQLException {
		String sql = "INSERT INTO USUARIO VALUES(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getSenha());
		ps.setInt(3, fKFuncionario);

		ps.execute();
		ps.close();

	}

	public Usuario buscaUsuarioUsuario(String usuario) throws SQLException {

		Usuario u = new Usuario();

		String sql = "SELECT ID_USUARIO, LOGIN, SENHA FROM USUARIO WHERE LOGIN = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, usuario);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			
		u.setIdUsuario(rs.getInt("ID_USUARIO"));
		u.setLogin(rs.getString("LOGIN"));
		u.setSenha(rs.getString("SENHA"));
		
		}
		
		rs.close();
		ps.close();

		return u;

	}

}
