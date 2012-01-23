package ASTSimple.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class GetInfo extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		
		// Obter todos os projetos na área de trabalho
		IProject[] projects = root.getProjects();
		
		// Loop sobre todos os projetos
		for (IProject project : projects) {
			try{
				if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")){
					IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
					// parse(JavaCore.create(project));				
					for (IPackageFragment mypackage : packages){
						if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE){
							for (ICompilationUnit unit : mypackage.getCompilationUnits()){
								// Agora crie o AST para o ICompilationUnits
								CompilationUnit parse = parse(unit);
								ClassVisitor classVisitor = new ClassVisitor();
								DeclarationVisitor declarationVisitor = new DeclarationVisitor();
								MethodVisitor methodVisitor = new MethodVisitor();
								parse.accept(classVisitor);
								parse.accept(declarationVisitor);
								parse.accept(methodVisitor);
								for (TypeDeclaration classe : classVisitor.getClasses()){
									System.out.println("\n\nNome da classe: " + classe.getName() + "Super classe: " + classe.getSuperclassType());
									for(FieldDeclaration declaration : declarationVisitor.getDeclarations()){
										System.out.println("Tipo variavel : " + declaration.getType());
									}
									for (MethodDeclaration method : methodVisitor.getMethods()){
										System.out.println("\n\nNome do metodo: " + method.getName() + " \nTipo de retorno do metodo: " + method.getReturnType2() + "\nParametro do metodo: " + method.parameters());
									}
								}
							}
						}
					}
				}
			}
			catch (CoreException e){
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
		return null;
	}

	
/** * Reads a ICompilationUnit and creates the AST DOM for manipulating the * Java source file * * @param unit * @return */
	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}
}
