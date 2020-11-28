package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.Conexao;
import entity.Livro;
import entity.Preco;

public class PrecoDao {

	private Connection c;

	public PrecoDao() throws ClassNotFoundException, SQLException {
		
		Conexao con = new Conexao();

		c = con.getConnection();
	}
	
	public int inserirPreco(Preco p) throws SQLException {
	
		int id = -1;

		String sql = "INSERT INTO PRECO VALUES( GETDATE(), ? ,?)";
		
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setDouble(1, p.getValor());
		ps.setInt(2, p.getFkLivroPreco());
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			id = rs.getInt(1);
		}

		rs.close();
		ps.close();

		return id;
		
	}
	
	public Preco buscaPrecoId(int id) throws SQLException {
		
		Preco p = new Preco();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.ID_PRECO, p.DATA_PRECO, p.VALOR, p.FK_LIVRO_PRECO  ");
		sql.append("FROM PRECO p ");
		sql.append("WHERE p.ID_PRECO = ? ");
		
		
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1,  id );
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			
			
			
			p.setIdPreco(rs.getInt("ID_PRECO"));
			p.setDataPreco(rs.getDate("DATA_PRECO"));
			p.setValor(rs.getDouble("VALOR"));
			p.setFkLivroPreco(rs.getInt("FK_LIVRO_PRECO"));
		
			
		}
		rs.close();
		ps.close();
		
		return p;
		
	}

}
