package Info;

import java.util.ArrayList;

public class Grafo {
	
	private ArrayList<ClasseInfo> grafo;
	
	public Grafo(ArrayList<ClasseInfo> grafo){
		this.grafo = grafo;
	}

	public ArrayList<ClasseInfo> getGrafo() {
		return grafo;
	}

	public void setGrafo(ArrayList<ClasseInfo> grafo) {
		this.grafo = grafo;
	}
	
}
