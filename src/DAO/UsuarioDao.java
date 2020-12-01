package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Dao do Usuario
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class UsuarioDao {

	/** Conexao c. */
	private Connection c;

	/**
	 * Classe que recupera a conexao com o Banco.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UsuarioDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();

	}

	/**
	 * Metodo que insere dados na tabela USUARIO do Banco de dados.
	 * 
	 * @param u
	 * @throws SQLException
	 */
	public void insereUsuario(Usuario u) throws SQLException {
		String sql = "INSERT INTO USUARIO VALUES(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getSenha());
		ps.setInt(3, u.getfKFuncionario());

		ps.execute();
		ps.close();
		

	}
	
	/**
	 *  Metodo que altera(atualiza) usuario.
	 * 
	 * @param u
	 * @throws SQLException
	 */
	public void alteraUsuario(Usuario u)throws SQLException {
		
		String sql = "update USUARIO set LOGIN = ?,SENHA = ? WHERE FK_FUNCIONARIO_USUARIO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getLogin());
		ps.setString(2, u.getSenha());
		ps.setInt(3, u.getfKFuncionario());

		ps.execute();
		ps.close();
		

	}
	
	/**
	 * Metodo que exclui usuario.
	 * 
	 * @param IdUsu
	 * @throws SQLException
	 */
	public void deletarUsuario(int IdUsu)throws SQLException {
		
		String sql = "Delete from USUARIO where ID_USUARIO = ?";
		PreparedStatement ps = c.prepareStatement(sql);;
		ps.setInt(1, IdUsu);

		ps.execute();
		ps.close();
		
	}

	/**
	 * Metodo que busca(pesquisa) usuario no Banco de dados.
	 * 
	 * @param usuario
	 * @return usuario
	 * @throws SQLException
	 */
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
	
	/**
	 * Metodo que busca usuario.
	 * 
	 * @param usuario
	 * @return true or false
	 * @throws SQLException
	 */
	public boolean buscaUsuario(String usuario) throws SQLException {

		boolean u = false;
		String sql = "SELECT ID_USUARIO, LOGIN, SENHA,FK_FUNCIONARIO_USUARIO FROM USUARIO WHERE LOGIN = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, usuario);
		
		ResultSet rs = ps.executeQuery();

		while (rs.next() && u == false) {
			if(rs.getString("LOGIN").equals(usuario)) {
				u = true;
			}
		}
		rs.close();
		ps.close();

		return u;

	}

	/**
	 * Metodo que busca a lista de usuarios.
	 * 
	 * @return lista de usuarios
	 * @throws SQLException
	 */
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

	/**
	 * Metodo que altera(atualiza) senha do usuario. 
	 */
	public void atualizarSenha(int idUsuario, String senhaNova) throws SQLException {

		String sql = "UPDATE USUARIO SET SENHA = ? WHERE ID_USUARIO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, senhaNova);
		ps.setInt(2, idUsuario);
		ps.close();

	}

}
