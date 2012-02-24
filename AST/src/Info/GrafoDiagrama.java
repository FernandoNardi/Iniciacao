package Info;

import java.util.ArrayList;
import java.util.List;

public class GrafoDiagrama {
	
	private List<Diagrama> grafo = new ArrayList<Diagrama>();
	
	public GrafoDiagrama(){}
	
	public GrafoDiagrama(List<Diagrama> grafo){
		this.grafo = grafo;
	}

	public List<Diagrama> getGrafo() {
		return grafo;
	}
	
	public Diagrama getGrafo(int i) {
		return this.grafo.get(i);
	}

	public void setGrafo(List<Diagrama> grafo) {
		this.grafo = grafo;
	}
	
	public void setGrafo(Diagrama nó) {
		this.grafo.add(nó);
	}
}
