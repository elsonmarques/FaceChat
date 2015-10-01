package Model;
//Classe da camada Modelo da arquitetura MVC para representação das relações entre agrupamentos
//contábeis filhos e pais no Banco de dados. Bean padrão.

public class QueryBean implements java.io.Serializable {
private int inteiro;
private String palavra;
public QueryBean(){}
public int getInteiro() {
	return inteiro;
}
public void setInteiro(int inteiro) {
	this.inteiro = inteiro;
}
public String getPalavra() {
	return palavra;
}
public void setPalavra(String palavra) {
	this.palavra = palavra;
}



	


 

}
