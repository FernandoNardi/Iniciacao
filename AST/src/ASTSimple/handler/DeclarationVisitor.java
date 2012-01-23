package ASTSimple.handler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;

public class DeclarationVisitor extends ASTVisitor{
	
	List<FieldDeclaration> declaration = new ArrayList<FieldDeclaration>();
	
	public boolean visit(FieldDeclaration node){
		this.declaration.add(node);
		return super.visit(node);
	}
	
	public List<FieldDeclaration> getDeclarations(){
		return this.declaration;
	} 
}
