package Model;
//Classe de acesso aos dados da tabela Visao. Data Access Object padrão.


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import Control.Conexao;
public class filhoPaiDAO {
	Connection connection;
public filhoPaiDAO(){
	this.connection = new Conexao().Conecta();
}
public void adiciona(filhoPaiBean filhoPai) throws SQLException{
	String sql = " set language BRAZILIAN; insert into Staging.projeto.vis_filho_pai (filho,ordem,pai,conta,visao) values (?,?,?,?,?)";
	PreparedStatement stmt = connection.prepareStatement(sql);
	//stmt.setInt(1, capa.getID());
	stmt.setString(1, filhoPai.getFilho());
	stmt.setInt(2, filhoPai.getOrdem());
	stmt.setString(3, filhoPai.getPai());
	stmt.setInt(4,filhoPai.getConta());
	stmt.setInt(5, filhoPai.getVisao());
	stmt.execute();
	stmt.close();

}
public void deleta(filhoPaiBean filhoPai) throws SQLException{
	String sql = "delete from projeto.vis_filho_pai where ID =?";
	PreparedStatement stmt = connection.prepareStatement(sql);
	stmt.setInt(1, filhoPai.getID());
	stmt.execute();
	stmt.close();
}
public void deletaTudo(visaoBean visao) throws SQLException{
	String sql = "delete from projeto.vis_filho_pai where visao =?";
	PreparedStatement stmt = connection.prepareStatement(sql);
	stmt.setInt(1, visao.getID());
	stmt.execute();
	stmt.close();
}

 public List<filhoPaiBean> getLista() {
 try {
	 List<filhoPaiBean> filhoPaiList = new ArrayList<filhoPaiBean>();
	 PreparedStatement stmt = this.connection.prepareStatement(" set language BRAZILIAN; select * from projeto.vis_filho_pai");
	 ResultSet rs = stmt.executeQuery();
	
	 while (rs.next()) {
	 // criando o objeto Filho-pai
		filhoPaiBean filhoPai = new filhoPaiBean();
		 filhoPai.setID(rs.getInt("ID"));
		 filhoPai.setFilho(rs.getString("filho"));
		filhoPai.setOrdem(rs.getInt("ordem"));
	     filhoPai.setPai(rs.getString("pai"));
	     filhoPai.setConta(rs.getInt("conta"));
	      filhoPai.setVisao(rs.getInt("visao"));
	    
	 // adicionando o objeto à lista
	filhoPaiList.add(filhoPai);
	 }
	 rs.close();
	 stmt.close();
	 return filhoPaiList;
	 } catch (SQLException e) {
	 throw new RuntimeException(e);
	 }
	 }
 
 		 
 public filhoPaiBean getFilhoPai(int id) {
	 filhoPaiBean filhoPai = new filhoPaiBean();
	 try{
		 PreparedStatement stmt = this.connection.prepareStatement(" set language BRAZILIAN; select top 1 * from projeto.vis_filho_pai where id= ? order by ID DESC");
		 stmt.setInt(1,id);
		 ResultSet rs = stmt.executeQuery();
		
		 while (rs.next()) {
		 // criando o objeto Visao
			
			 filhoPai.setID(rs.getInt("ID"));
			 filhoPai.setFilho(rs.getString("filho"));
			 filhoPai.setOrdem(rs.getInt("ordem"));
		     filhoPai.setPai(rs.getString("pai"));
		     filhoPai.setConta(rs.getInt("conta"));
		     filhoPai.setVisao(rs.getInt("visao"));
		     		 }
		 rs.close();
		 stmt.close();
		 return filhoPai;
		 } catch (SQLException e) {
		 throw new RuntimeException(e);
		 }
		 }

 
  @SuppressWarnings("deprecation")
public void altera(filhoPaiBean filhoPai) {
	//  (codigoGrupoContabil,status,posicao,descricao,inicio,fim)
	  String sql = " set language BRAZILIAN; update projeto.vis_filho_pai set filho=?, ordem=?,pai=?,conta=?,visao=? where ID=?";
	
	  try {
	  PreparedStatement stmt = connection.prepareStatement(sql);
	  stmt.setString(1,filhoPai.getFilho());
	  stmt.setInt(2,filhoPai.getOrdem());
	  stmt.setString(3, filhoPai.getPai());
	  stmt.setInt(4, filhoPai.getConta());
	  stmt.setInt(5, filhoPai.getVisao());
	  stmt.setInt(6,filhoPai.getID());
	  stmt.execute();
	  stmt.close();
	  } catch (SQLException e) {
	  throw new RuntimeException(e);
}
}}