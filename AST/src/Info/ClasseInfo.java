package Info;

import java.util.*;
import org.eclipse.jdt.core.dom.*;

public class ClasseInfo {
	
	TypeDeclaration classe;
	List<MethodDeclaration> metodo = new ArrayList<MethodDeclaration>();
	List<FieldDeclaration> declaration = new ArrayList<FieldDeclaration>();
	
	public ClasseInfo(){}
}
