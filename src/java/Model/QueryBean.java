package Model;
//Classe da camada Modelo da arquitetura MVC para representa��o das rela��es entre agrupamentos
//cont�beis filhos e pais no Banco de dados. Bean padr�o.

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
