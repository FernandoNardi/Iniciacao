package ASTSimple.handler;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MethodInvocationVisitor extends ASTVisitor{
	
	List<MethodInvocation> methodsInvocations = new ArrayList<MethodInvocation>();

	@Override
	public boolean visit(MethodInvocation node) {
		methodsInvocations.add(node);
		return super.visit(node);
	}

	public List<MethodInvocation> getMethodsInvocations() {
		return methodsInvocations;
	}

}
