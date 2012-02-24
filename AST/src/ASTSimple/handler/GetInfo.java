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
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class GetInfo extends AbstractHandler {
	
	private GrafoDiagrama grafo = new GrafoDiagrama();
	
	private TypeDeclaration classeDeclaration;
	private List<MethodDeclaration> metodoDeclaration;
	private List<Aresta> arestas;
	
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
								InstanceVisitor instanceVisitor = new InstanceVisitor();
								
								parse.accept(classVisitor);
								parse.accept(declarationVisitor);
								parse.accept(methodVisitor);
								parse.accept(methodInvocationVisitor);
								parse.accept(instanceVisitor);
								
								metodoDeclaration = new ArrayList<MethodDeclaration>();
								arestas = new ArrayList<Aresta>();
								
								for (TypeDeclaration classe : classVisitor.getClasses()){
									if(classe.getSuperclassType() != null){
										arestas.add(new ArestaHeranca(classe.getSuperclassType().toString()));
									}//aresta de herança -->>> extends
									System.out.println("classe: " + classe.getName());
									
									int i = 0;
									while(i < classe.superInterfaceTypes().size()){
										arestas.add(new ArestaDeInterface(classe.superInterfaceTypes().get(i).toString()));
										++i;
									}//aresta de inteface --->>> implements
									
									classeDeclaration = classe;
									for(FieldDeclaration declaration : declarationVisitor.getDeclarations()){
										if(!declaration.getType().isPrimitiveType()){
											if(!(declaration.getType().toString()).equals("String")){
												arestas.add(new ArestaAssociacao(declaration.getType().toString(), declaration));
											}
										}
									}// aresta de associação     
									
									for (MethodDeclaration method : methodVisitor.getMethods()){
										if(!method.isConstructor()){
											metodoDeclaration.add(method);
										}
									}
									
									for(ClassInstanceCreation instanceCreation : instanceVisitor.getInstances()){
										arestas.add(new ArestaDeInstancia(instanceCreation.getType().toString(), instanceCreation));
									}
									
									for(MethodInvocation methodInvocation : methodInvocationVisitor.getMethodsInvocations()){
										arestas.add(new ArestaChamadaDeMetodo(methodInvocation.resolveMethodBinding().getDeclaringClass().getName().toString(), methodInvocation));
									}
									
									grafo.setGrafo(new Diagrama(classeDeclaration, metodoDeclaration, arestas));
								}
							}
						}
					}
				}
			}
			catch (CoreException e){
				e.printStackTrace();
			}
			
			GrafoVisitor t = new GrafoVisitor(grafo);
			
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
}
 