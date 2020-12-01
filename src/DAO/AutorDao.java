package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entity.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Dao do Autor
 * 
 * @author Adriano, Gustavo, Roberto
 *
 */
public class AutorDao {
	
	/** Conexao c. */
	private Connection c;
	
	/**
	 * Classe que recupera a conex�o com o Banco.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public AutorDao() throws ClassNotFoundException, SQLException {

		Conexao con = new Conexao();
		c = con.getConnection();
	}


	/**
	 * M�todo que insere os dados do autor no Banco de dados.
	 * @param autor
	 * @throws SQLException
	 */
	public void insereAutor(Autor autor) throws SQLException {
		
		String sql = "INSERT INTO AUTOR VALUES (?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, autor.getNome());
		ps.setString(2, autor.getNacionalidade());

		ps.executeUpdate();


		ps.close();
		
		
	}
	
	/**
	 * M�todo que altera (atualiza) os dados do autor no Banco de Dados.
	 * @param a
	 * @throws SQLException
	 */
	public void alteraAutor(Autor a) throws SQLException {
		String sql = "UPDATE AUTOR SET NOME = ?, NACIONALIDADE = ? where ID_AUTOR = ? ";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getNome());
		ps.setString(2, a.getNacionalidade());
		ps.setInt(3, a.getIdAutor());

		ps.executeUpdate();


		ps.close();
	}
	
	/**
	 *M�todo que lista os autores inseridos no Banco de dados.
	 * 
	 * @return lista de autores
	 * @throws SQLException
	 */
	public ObservableList<Autor> listarAutores() throws SQLException{
		ObservableList<Autor> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from AUTOR");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Autor a = new Autor();
			a.setIdAutor(rs.getInt("ID_AUTOR"));
			a.setNome(rs.getString("NOME"));
			a.setNacionalidade(rs.getString("NACIONALIDADE"));
			lista.add(a);
		}
		
		return lista;
	}
	
	/**
	 * M�todo que verifica se tem um nome duplicado no Banco de dados.
	 * 
	 * @param Nome
	 * @return true or false
	 * @throws SQLException
	 */
	public boolean verificaDuplicNome(String Nome) throws SQLException {
		boolean u = false;
		String sql = "select NOME from AUTOR where NOME = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, Nome);
		
		ResultSet rs = ps.executeQuery();

		while (rs.next() && u == false) {
			if(rs.getString("NOME").equals(Nome)) {
				u = true;
			}
		}
		
		rs.close();
		ps.close();

		return u;
	}
	
	/**
	 * M�todo que faz a busca(pesquisa) dos autores que foram inseridos no Banco de dados. 
	 * 
	 * @param nome
	 * @return lista
	 * @throws SQLException
	 */
	public ObservableList<Autor> pesquisarAutores(String nome) throws SQLException{
		ObservableList<Autor> lista = FXCollections.observableArrayList();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from AUTOR where NOME like ?");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, nome+"%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Autor a = new Autor();
			a.setIdAutor(rs.getInt("ID_AUTOR"));
			a.setNome(rs.getString("NOME"));
			a.setNacionalidade(rs.getString("NACIONALIDADE"));
			lista.add(a);
		}
		
		return lista;
	}

}
