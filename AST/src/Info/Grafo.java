package Info;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	private List<ClasseInfo> grafo = new ArrayList<ClasseInfo>();
	
	public Grafo(){}
	
	public Grafo(List<ClasseInfo> grafo){
		this.grafo = grafo;
	}

	public List<ClasseInfo> getGrafo() {
		return grafo;
	}
	
	public ClasseInfo getGrafo(int i) {
		return this.grafo.get(i);
	}

	public void setGrafo(List<ClasseInfo> grafo) {
		this.grafo = grafo;
	}
	
	public void setGrafo(ClasseInfo nó) {
		this.grafo.add(nó);
	}
}
