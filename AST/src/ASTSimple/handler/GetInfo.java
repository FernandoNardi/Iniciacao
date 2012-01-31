package ASTSimple.handler;

import java.util.ArrayList;
import java.util.List;

import Info.*;

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
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class GetInfo extends AbstractHandler {
	
	private List<ClasseInfo> no = new ArrayList<ClasseInfo>();
	private TypeDeclaration classeDeclaration;
	private List<MethodDeclaration> metodoDeclaration;
	private List<FieldDeclaration> fieldDeclaration;
	private List<MethodInvocation> metodoInvocation;
	
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
								MethodInvocationVisitor methodInvocationVisitor = new MethodInvocationVisitor();
								
								parse.accept(classVisitor);
								parse.accept(declarationVisitor);
								parse.accept(methodVisitor);
								parse.accept(methodInvocationVisitor);
								
								metodoDeclaration = new ArrayList<MethodDeclaration>();
								fieldDeclaration = new ArrayList<FieldDeclaration>();
								metodoInvocation = new ArrayList<MethodInvocation>();
								
								for (TypeDeclaration classe : classVisitor.getClasses()){
									System.out.println("\n\nNome da classe: " + classe.getName() + "  Super classe: " + classe.getSuperclassType() + "  Interface implemets: "+ classe.superInterfaceTypes() + "  teste 2: " + classe.isInterface());
									if(classe.modifiers().size() >= 2){
										//System.out.println("teste" + classe.modifiers().get(1));
									}
									classeDeclaration = classe;
									for(FieldDeclaration declaration : declarationVisitor.getDeclarations()){
										//System.out.println("Tipo variavel : " + declaration.getType());
										fieldDeclaration.add(declaration);
									}
									for(MethodInvocation methodInvocation : methodInvocationVisitor.getMethodsInvocations()){
										//System.out.println("Metodo invocado : " + methodInvocation.getName() + " tetste: ");
										metodoInvocation.add(methodInvocation);
									}
									for (MethodDeclaration method : methodVisitor.getMethods()){
										//System.out.println("\n\nNome do metodo: " + method.getName());// + " \nTipo de retorno do metodo: " + method.getReturnType2() + "\nParametro do metodo: " + method.parameters() + " Test: " + method.isConstructor());
										metodoDeclaration.add(method);
									}
									no.add(new ClasseInfo(classeDeclaration, metodoDeclaration, fieldDeclaration, metodoInvocation));
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
	
	private void arestas(){
		int i = 0;
		Aresta aresta;
		while(i < no.size()){
			if(this.no.get(i).getClassDeclaration().getSuperclassType() != null){
				for(int j = 0; j < no.size(); ++j){
					if(this.no.get(i).getClassDeclaration().getSuperclassType().equals(this.no.get(j).getClassDeclaration().getName()) && !this.no.get(i).getClassDeclaration().isInterface()){
						aresta = new ArestaHeranca(this.no.get(i).getClassDeclaration().getSuperclassType().toString());
					}
				}
			}
			++i;
		}
	}
}
