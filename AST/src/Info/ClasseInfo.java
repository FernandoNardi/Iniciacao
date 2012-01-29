package Info;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class ClasseInfo {
	
	private TypeDeclaration classDeclaration;
	List<MethodDeclaration> methodDeclaration = new ArrayList<MethodDeclaration>();
	List<FieldDeclaration> fieldDeclaration = new ArrayList<FieldDeclaration>();
	List<MethodInvocation> methodInvocation = new ArrayList<MethodInvocation>();
	List<Aresta> aresta = new ArrayList<Aresta>();
	
	public ClasseInfo(){} //Construtor default
	
	public ClasseInfo(TypeDeclaration c, List<MethodDeclaration> m, List<FieldDeclaration> d, List<MethodInvocation> mi){
		setClassDeclaration(c);
		setMethodDeclaration(m);
		setFieldDeclaration(d);
		setMethodInvocation(mi);
	}

	public TypeDeclaration getClassDeclaration() {
		return classDeclaration;
	}

	public void setClassDeclaration(TypeDeclaration classDeclaration) {
		this.classDeclaration = classDeclaration;
	}

	public List<MethodDeclaration> getMethodDeclaration() {
		return methodDeclaration;
	}

	public void setMethodDeclaration(List<MethodDeclaration> methodDeclaration) {
		this.methodDeclaration = methodDeclaration;
	}

	public List<FieldDeclaration> getFieldDeclaration() {
		return fieldDeclaration;
	}

	public void setFieldDeclaration(List<FieldDeclaration> fieldDeclaration) {
		this.fieldDeclaration = fieldDeclaration;
	}

	public List<MethodInvocation> getMethodInvocation() {
		return methodInvocation;
	}

	public void setMethodInvocation(List<MethodInvocation> methodInvocation) {
		this.methodInvocation = methodInvocation;
	}
}
