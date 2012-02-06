package Info;

import org.eclipse.jdt.core.dom.ClassInstanceCreation;

public abstract class Aresta {
	
	private String nome;
	
	public Aresta(){}// construtor default
	
	public Aresta(String n){
		this.nome = n;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public abstract Object getObject();
	
}
