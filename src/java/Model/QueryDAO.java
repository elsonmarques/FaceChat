package Model;
//Classe de acesso aos dados da tabela Visao. Data Access Object padrão.


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import Control.Conexao;
public class QueryDAO {
	Connection connection;
public QueryDAO(){
	this.connection = new Conexao().Conecta();
}
 public List<QueryBean> getNumeros() {
 try {
	 List<QueryBean> queryList = new ArrayList<QueryBean>();
	 PreparedStatement stmt = this.connection.prepareStatement(" select * from contador()");
	 ResultSet rs = stmt.executeQuery();
	
	 while (rs.next()) {
	 // criando o objeto Visao
		QueryBean query = new QueryBean();
		 query.setInteiro(rs.getInt("numero"));
		 query.setPalavra(rs.getString("numero"));
	
	 // adicionando o objeto à lista
	queryList.add(query);
	 }
	 rs.close();
	 stmt.close();
	 return queryList;
	 } catch (SQLException e) {
	 throw new RuntimeException(e);
	 }
	 }
 
 public List<QueryBean> getContas() {
	 try {
		 List<QueryBean> queryList = new ArrayList<QueryBean>();
		 PreparedStatement stmt = this.connection.prepareStatement(" select * from [dbo].[Contas]() ");
		 ResultSet rs = stmt.executeQuery();
		
		 while (rs.next()) {
		 // criando o objeto Visao
			QueryBean query = new QueryBean();
			 query.setInteiro(rs.getInt("conta"));
			 query.setPalavra(rs.getString("descricao"));
		
		 // adicionando o objeto à lista
		queryList.add(query);
		 }
		 rs.close();
		 stmt.close();
		 return queryList;
		 } catch (SQLException e) {
		 throw new RuntimeException(e);
		 }
		 }
 		 

}