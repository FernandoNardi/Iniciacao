package Info;

public class ArestaChamadaDeMetodo extends Aresta{

	private String nomeMetodoChamado;
	private String metodoLocalChamado; //em qual metodo houve a chamada
	
	public ArestaChamadaDeMetodo(String nomeClasse, String nomeMetodoChamado, String metodoLocalChamado){
		super(nomeClasse);
		this.nomeMetodoChamado = nomeMetodoChamado;
		this.metodoLocalChamado = metodoLocalChamado;
	}

	public String getNomeMetodoChamado() {
		return nomeMetodoChamado;
	}

	public void setNomeMetodoChamado(String nomeMetodoChamado) {
		this.nomeMetodoChamado = nomeMetodoChamado;
	}

	public String getMetodoLocalChamado() {
		return metodoLocalChamado;
	}

	public void setMetodoLocalChamado(String metodoLocalChamado) {
		this.metodoLocalChamado = metodoLocalChamado;
	}
	
}
