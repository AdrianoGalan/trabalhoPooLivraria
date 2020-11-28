package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Conexao;
import entity.Preco;
import javafx.collections.ObservableList;
import tabelaModel.ModelItensVenda;

public class VendaDao {
	
	private Connection c;

	public VendaDao() throws ClassNotFoundException, SQLException {
		
		Conexao con = new Conexao();

		c = con.getConnection();
	}
	
	public int inserirVenda(int idCliente) throws SQLException {
		
		int id = -1;

		String sql = "INSERT INTO VENDA VALUES( GETDATE(), ?)";
		
		PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.setDouble(1, idCliente);
		
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			id = rs.getInt(1);
		}

		rs.close();
		ps.close();

		return id;
		
	}


}
