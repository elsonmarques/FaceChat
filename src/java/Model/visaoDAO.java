package Model;
//Classe de acesso aos dados da tabela Visao. Data Access Object padrão.


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import Control.Conexao;
public class visaoDAO {
	Connection connection;
public visaoDAO(){
	this.connection = new Conexao().Conecta();
}
public void adiciona(visaoBean visao) throws SQLException{
	String sql = " set language BRAZILIAN; insert into Staging.projeto.vis_visao (nome,descricao,timestamp,n_niveis) values (?,?,?,?)";
	PreparedStatement stmt = connection.prepareStatement(sql);
	//stmt.setInt(1, capa.getID());
	stmt.setString(1, visao.getNome());
	stmt.setString(2, visao.getDescricao());
	stmt.setString(3, visao.getTimestamp());
	stmt.setInt(4, visao.getN_niveis());
	stmt.execute();
	stmt.close();
	limpa();
}
public void deleta(visaoBean visao) throws SQLException{
	String sql = "delete from Staging.projeto.vis_visao where ID =?";
	PreparedStatement stmt = connection.prepareStatement(sql);
	stmt.setInt(1, visao.getID());
	stmt.execute();
	stmt.close();
}

public void deleta(String visao) throws SQLException{
	String sql = "delete from Staging.projeto.vis_visao where ID =?";
	PreparedStatement stmt = connection.prepareStatement(sql);
	stmt.setInt(1, Integer.parseInt( visao));
	stmt.execute();
	stmt.close();
	
}
public void limpa()  throws SQLException{
	String sql = "delete from Staging.projeto.vis_filho_pai where filho is null ";
	PreparedStatement stmt = connection.prepareStatement(sql);
//	stmt.setInt(1, 0);
	stmt.execute();
	stmt.close();
	
}
 public List<visaoBean> getLista() {
 try {
	 List<visaoBean> visaoList = new ArrayList<visaoBean>();
	 PreparedStatement stmt = this.connection.prepareStatement(" set language BRAZILIAN; select * from Staging.projeto.vis_visao");
	 ResultSet rs = stmt.executeQuery();
	
	 while (rs.next()) {
	 // criando o objeto Visao
		visaoBean visao = new visaoBean();
		 visao.setID(rs.getInt("ID"));
		 visao.setDescricao(rs.getString("descricao"));
		 visao.setNome(rs.getString("nome"));
	     visao.setTimestamp(rs.getString("timestamp"));
	     visao.setN_niveis(rs.getInt("n_niveis"));
	
	 // adicionando o objeto à lista
	visaoList.add(visao);
	 }
	 rs.close();
	 stmt.close();
	 return visaoList;
	 } catch (SQLException e) {
	 throw new RuntimeException(e);
	 }
	 }
 
 		 
 public visaoBean getVisao(int id) {
	 visaoBean visao = new visaoBean();
	 try{
		 PreparedStatement stmt = this.connection.prepareStatement(" set language BRAZILIAN; select top 1 * from from Staging.projeto.vis_visao where id= ? order by ID DESC");
		 stmt.setInt(1,id);
		 ResultSet rs = stmt.executeQuery();
		
		 while (rs.next()) {
		 // criando o objeto Visao
			
			 visao.setID(rs.getInt("ID"));
			 visao.setDescricao(rs.getString("descricao"));
			 visao.setNome(rs.getString("nome"));
		     visao.setTimestamp(rs.getString("timestamp"));
		     visao.setN_niveis(rs.getInt("n_niveis"));
		
		 }
		 rs.close();
		 stmt.close();
		 return visao;
		 } catch (SQLException e) {
		 throw new RuntimeException(e);
		 }
		 }
 public int getID(visaoBean visao) {
	 int resposta=0;
	 try{
		 PreparedStatement stmt = this.connection.prepareStatement(" set language BRAZILIAN; select id  from Staging.projeto.vis_visao where nome= ? order by ID DESC");
		 stmt.setString(1,visao.getNome());
		 ResultSet rs = stmt.executeQuery();
		
		 while (rs.next()) {
		 // criando o objeto Visao
			
			resposta= rs.getInt("id");
		 }
		 rs.close();
		 stmt.close();
		 return resposta;
		 } catch (SQLException e) {
		 throw new RuntimeException(e);
		 }
		 }
 
  @SuppressWarnings("deprecation")
public void altera(visaoBean visao) {
	//  (codigoGrupoContabil,status,posicao,descricao,inicio,fim)
	  String sql = " set language BRAZILIAN; update from Staging.projeto.vis_visao set nome=?, descricao=?,timestamp=?, where ID=?";
	
	  try {
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  stmt.setString(1,visao.getNome());
	  stmt.setString(2,visao.getDescricao());
	  stmt.setString(3, visao.getTimestamp());
	  stmt.setInt(4, visao.getID());
	  stmt.execute();
	  stmt.close();
	  } catch (SQLException e) {
	  throw new RuntimeException(e);
}
}}