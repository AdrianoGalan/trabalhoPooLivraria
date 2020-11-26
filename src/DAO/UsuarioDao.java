package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioDao {

	private Connection c;

	public UsuarioDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();

	}

	public void insereUsuario(Usuario u) throws SQLException {
		String sql = "INSERT INTO USUARIO VALUES(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getSenha());
		ps.setInt(3, u.getfKFuncionario());

		ps.execute();
		ps.close();
		

	}

	public Usuario buscaUsuarioUsuario(String usuario) throws SQLException {

		Usuario u = new Usuario();

		String sql = "SELECT ID_USUARIO, LOGIN, SENHA,FK_FUNCIONARIO_USUARIO FROM USUARIO WHERE LOGIN = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, usuario);
		
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			u.setIdUsuario(rs.getInt("ID_USUARIO"));
			u.setLogin(rs.getString("LOGIN"));
			u.setSenha(rs.getString("SENHA"));
			u.setfKFuncionario(rs.getInt("FK_FUNCIONARIO_USUARIO"));

		}

		rs.close();
		ps.close();

		return u;

	}
	
	public ObservableList<Usuario> buscaListaUsuarios() throws SQLException{
		
		ObservableList<Usuario> lista = FXCollections.observableArrayList();
		String sql = "SELECT ID_USUARIO, LOGIN, SENHA,FK_FUNCIONARIO_USUARIO FROM USUARIO";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Usuario u;
		while (rs.next()) {
			u = new Usuario();
			u.setIdUsuario(rs.getInt("ID_USUARIO"));
			u.setLogin(rs.getString("LOGIN"));
			u.setSenha(rs.getString("SENHA"));
			u.setfKFuncionario(rs.getInt("FK_FUNCIONARIO_USUARIO"));
			
			lista.add(u);
		}

		rs.close();
		ps.close();

		return lista;
		
	}

	public void atualizarSenha(int idUsuario, String senhaNova) throws SQLException {

		String sql = "UPDATE USUARIO SET SENHA = ? WHERE ID_USUARIO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, senhaNova);
		ps.setInt(2, idUsuario);
		ps.close();

	}

}
