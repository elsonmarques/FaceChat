package Model;
//Classe da camada Modelo da arquitetura MVC para representação das relações entre agrupamentos
//contábeis filhos e pais no Banco de dados. Bean padrão.

public class filhoPaiBean implements java.io.Serializable {
private int ID;
private String filho;
private String pai;
private int ordem;
private int conta;
private int visao;
public filhoPaiBean(){}
public int getID() {
	return ID;
}

public void setID(int iD) {
	ID = iD;
}
public String getFilho() {
	return filho;
}

public void setFilho(String filho) {
	this.filho = filho;
}

public String getPai() {
	return pai;
}

public void setPai(String pai) {
	this.pai = pai;
}

public int getOrdem() {
	return ordem;
}

public void setOrdem(int ordem) {
	this.ordem = ordem;
}

public int getConta() {
	return conta;
}

public void setConta(int conta) {
	this.conta = conta;
}

public int getVisao() {
	return visao;
}

public void setVisao(int visao) {
	this.visao = visao;
}



	


 

}
