package Info;

import java.util.List;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Diagrama {
	
	private TypeDeclaration classDeclaration;
	private List<MethodDeclaration> methodDeclaration;
	private List<Aresta> arestas;
	
	public Diagrama(){} //Construtor default
	
	public Diagrama(TypeDeclaration c, List<MethodDeclaration> m, List<Aresta> a){
		setClassDeclaration(c);
		setMethodDeclaration(m);
		setAresta(a);
	}

	public TypeDeclaration getClassDeclaration() {
		return this.classDeclaration;
	}

	public void setClassDeclaration(TypeDeclaration classDeclaration) {
		this.classDeclaration = classDeclaration;
	}

	public List<MethodDeclaration> getMethodDeclaration() {
		return this.methodDeclaration;
	}
	
	public MethodDeclaration getMethodDeclaration(int i) {
		return this.methodDeclaration.get(i);
	}

	public void setMethodDeclaration(List<MethodDeclaration> methodDeclaration) {
		this.methodDeclaration = methodDeclaration;
	}

	public List<Aresta> getAresta() {
		return this.arestas;
	}

	public void setAresta(List<Aresta> aresta) {
		this.arestas = aresta;
	}
	
	public void setAresta(Aresta aresta) {
		this.arestas.add(aresta);
	}
}
