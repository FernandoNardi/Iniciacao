package Info;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class ClasseInfo {
	
	private TypeDeclaration classDeclaration;
	private List<MethodDeclaration> methodDeclaration;
	//private List<FieldDeclaration> fieldDeclaration;
	private List<MethodInvocation> methodInvocation;
	private List<Aresta> arestas = new ArrayList<Aresta>();
	
	public ClasseInfo(){} //Construtor default
	
	public ClasseInfo(TypeDeclaration c, List<MethodDeclaration> m, List<MethodInvocation> mi, List<Aresta> a){
		setClassDeclaration(c);
		setMethodDeclaration(m);
	//	setFieldDeclaration(d);
		setMethodInvocation(mi);
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
/*
	public List<FieldDeclaration> getFieldDeclaration() {
		return this.fieldDeclaration;
	}

	public void setFieldDeclaration(List<FieldDeclaration> fieldDeclaration) {
		this.fieldDeclaration = fieldDeclaration;
	}
*/
	public List<MethodInvocation> getMethodInvocation() {
		return this.methodInvocation;
	}
	
	public MethodInvocation getMethodInvocation(int i) {
		return this.methodInvocation.get(i);
	}

	public void setMethodInvocation(List<MethodInvocation> methodInvocation) {
		this.methodInvocation = methodInvocation;
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
