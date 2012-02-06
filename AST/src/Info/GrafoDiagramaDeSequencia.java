package Info;

import java.util.ArrayList;
import java.util.List;

public class GrafoDiagramaDeSequencia {
	
	private List<DiagramaDeSequencia> grafo = new ArrayList<DiagramaDeSequencia>();

	public GrafoDiagramaDeSequencia(){}
	
	public GrafoDiagramaDeSequencia(List<DiagramaDeSequencia> grafo) {
		this.grafo = grafo;
	}

	public List<DiagramaDeSequencia> getGrafo() {
		return grafo;
	}
	
	public DiagramaDeSequencia getGrafo(int i) {
		return grafo.get(i);
	}

	public void setGrafo(List<DiagramaDeSequencia> grafo) {
		this.grafo = grafo;
	}
	
	public void setGrafo(DiagramaDeSequencia grafo) {
		this.grafo.add(grafo);
	}
}
