package Model;
//Classe da camada Modelo da arquitetura MVC para representação das visões no Banco de dados. Bean padrão.

public class visaoBean implements java.io.Serializable {
private int ID;
private String descricao;
private String timestamp;
private int n_niveis;
private String nome;

public int getID() {
	return ID;
}

public void setID(int iD) {
	ID = iD;
}


public String getNome() {
	return nome;
}



public void setNome(String nome) {
	this.nome = nome;
}



public String getDescricao() {
	return descricao;
}



public void setDescricao(String descricao) {
	this.descricao = descricao;
}



public String getTimestamp() {
	return timestamp;
}



public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}



public int getN_niveis() {
	return n_niveis;
}



public void setN_niveis(int n_niveis) {
	this.n_niveis = n_niveis;
}







	public visaoBean(){}


 

}
