package ASTSimple.handler;

import java.util.*;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ClassVisitor extends ASTVisitor{
	
	List<TypeDeclaration> classe = new ArrayList<TypeDeclaration>(); 
	
	public boolean visit(TypeDeclaration node){
		classe.add(node);
		//classe.add(node.getName());
		return super.visit(node);
	}
	
	public List<TypeDeclaration> getClasses (){
		return this.classe;
	}
}
