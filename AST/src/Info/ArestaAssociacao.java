package Info;

public class ArestaAssociacao extends Aresta{
	
	private String nomeMetodo;
	
	public ArestaAssociacao(String tipoClasse, String nomeMetodo){
		super(tipoClasse);
		this.nomeMetodo = nomeMetodo;
	}

	public String getNomeMetodo() {
		return nomeMetodo;
	}

	public void setNomeMetodo(String nomeMetodo) {
		this.nomeMetodo = nomeMetodo;
	}
	
}
