package Info;

import java.util.List;

public class DiagramaDeSequencia {
	
	private List<Aresta> arestas;
	
	public DiagramaDeSequencia (){ }
	
	public DiagramaDeSequencia (List<Aresta> arestas){
		this.arestas = arestas;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}
	
	public Aresta getArestas(int i) {
		return arestas.get(i);
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
	}
}
