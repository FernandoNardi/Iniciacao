package ASTSimple.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;

public class InstanceVisitor extends ASTVisitor{

	List<ClassInstanceCreation> instances = new ArrayList<ClassInstanceCreation>();
	
	public boolean visit(ClassInstanceCreation node){
		this.instances.add(node);
		return super.visit(node);
	}
	
	public List<ClassInstanceCreation> getInstances(){
		return this.instances;
	} 
}
