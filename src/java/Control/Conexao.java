
package Control;
/**
  *
  * @author elsonms
  * @version 1.0
  */
import java.sql.*;
import java.util.TreeMap;

public class Conexao {

     public static Connection con = null;
     protected static String connectionUrl;
     protected static Statement stm = null;
     protected static ResultSet rs = null;
     TreeMap<String, Integer> arvore;

     public void conecta() {
         try {
        	// System.out.println("conectou");
             // cria conexao
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(connectionUrl);
         } catch (SQLException e) {
             System.out.println("SQL Exception: " + e.toString());
         } catch (ClassNotFoundException cE) {
             System.out.println("Classe não encontrada: " + cE.toString());
         }
     }

     public void desconecta() {
         try {
             con.close();
             con = null;
             stm.close();
             stm = null;
             rs.close();
         } catch (SQLException ex) {
             System.out.print("SQLException: ");
             System.out.println(ex.getMessage());
         }
        // System.out.println(con);
     }

   public Connection Conecta() {
         try {
             return  
DriverManager.getConnection("jdbc:sqlserver://dmdw:1433; databaseName=Staging;user=sa;password=82818284");
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }
   public Connection ConectaUnificado() {
       try {
           return  
DriverManager.getConnection("jdbc:sqlserver://dmdw:1433; databaseName=Unificado;user=sa;password=82818284");
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
   public Connection ConectaLocalhost() {
         try {
             return  
DriverManager.getConnection("jdbc:sqlserver://10.17.39.95:1433;databaseName=Configuracao;user=sa;password=");
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }

   public void setConnetionURL(String servidor, String database, String  
usuario, String senha) {
         connectionUrl = "jdbc:sqlserver://" + servidor + ":1433;databaseName=" + database + ";user=" + usuario + ";password=" + senha + "";
         conecta();
     }
public void setConnetionDMDW() {
         connectionUrl = "jdbc:sqlserver://dmdw:1433;databaseName=configuracao;user=ETLbyProgram;password=DMDWownAction";
         conecta();
     }
public void setConnetionLocalhost() {
    // connectionUrl = "jdbc:sqlserver://187.11.232.29:1433;  
//databaseName=configuracao;user=sa;password=Adm2050";
         conecta();
     }
     public ResultSet select(String sql) {
         try {
             stm = con.createStatement();
             rs = stm.executeQuery(sql);
         } catch (Exception e) {
             System.out.println(e.toString());
             insere(arvore, e.toString(), 1);
         }
         return rs;
     }


     public int update(String sql) {
         int resultado = 0;
         try {
             stm = con.createStatement();
             resultado = stm.executeUpdate(sql);
         } catch (Exception e) {
             System.out.println(e.toString());
             insere(arvore, e.toString(), 1);
         }
         return resultado;
     }


     public int insert(String sql) {
         int resultado = 0;
         try {
             stm = con.createStatement();
             resultado = stm.executeUpdate(sql);
         } catch (Exception e) {
             System.out.println(e.toString());
             insere(arvore, e.toString(), 1);
         }
         return resultado;

     }

     public static void insere(TreeMap<String, Integer> a, String s,  
Integer i) {
         if (!a.containsKey(s)) {
             a.put(s, i);
         } else {
             Integer I2 = a.get(s) + 1;
             a.put(s, I2);
         }

     }

//    Para ser utilizado nas classes principais
//    int tamanho = conn.arvore.size();
//        for (int i = 0; i < tamanho; i++){
//        //erronoprojeto.Main.setErro("DESCRIÇÃO", "11111", "ATUALIZA  
//DIMENSÃO UNIDADE", conn.arvore.firstEntry().toString());
//        System.out.println(conn.arvore.firstEntry().toString());
//        conn.arvore.pollFirstEntry();
//    }

     public void execProcedure() {
         CallableStatement cs;
         try {
             // chama procedure sem passar parâmetro
             cs = con.prepareCall("{call proc_teste}");
             cs.execute();

             // Call a procedure with one IN parameter
             cs = con.prepareCall("{ call proc_teste_com_parametro(?)}");
             cs.setString(1, "descricao3");
             cs.execute();
             /**
             // Call a procedure with one OUT parameter
             cs = con.prepareCall("{call myprocout(?)}");

             // Register the type of the OUT parameter
             cs.registerOutParameter(1, Types.VARCHAR);

             // Execute the stored procedure and retrieve the OUT value
             cs.execute();
             String outParam = cs.getString(1);     // OUT parameter

             // Call a procedure with one IN/OUT parameter
             cs = con.prepareCall("{call myprocinout(?)}");

             // Register the type of the IN/OUT parameter
             cs.registerOutParameter(1, Types.VARCHAR);

             // Set the value for the IN/OUT parameter
             cs.setString(1, "a string");

             // Execute the stored procedure and retrieve the IN/OUT value
             cs.execute();
             outParam = cs.getString(1);            // OUT parameter
              */
         } catch (SQLException e) {
             System.out.println(e.toString());
             insere(arvore, e.toString(), 1);
         }
     }


}