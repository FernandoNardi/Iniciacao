package Info;

import java.util.ArrayList;
import java.util.List;

public class GrafoDiagramaDeClasse {
	
	private List<DiagramaDeClasse> grafo = new ArrayList<DiagramaDeClasse>();
	
	public GrafoDiagramaDeClasse(){}
	
	public GrafoDiagramaDeClasse(List<DiagramaDeClasse> grafo){
		this.grafo = grafo;
	}

	public List<DiagramaDeClasse> getGrafo() {
		return grafo;
	}
	
	public DiagramaDeClasse getGrafo(int i) {
		return this.grafo.get(i);
	}

	public void setGrafo(List<DiagramaDeClasse> grafo) {
		this.grafo = grafo;
	}
	
	public void setGrafo(DiagramaDeClasse nó) {
		this.grafo.add(nó);
	}
}
