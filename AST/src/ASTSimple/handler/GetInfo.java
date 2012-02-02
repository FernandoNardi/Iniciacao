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
	
	private static Grafo grafo = new Grafo();
	private TypeDeclaration classeDeclaration;
	private List<MethodDeclaration> metodoDeclaration;
	//private List<FieldDeclaration> fieldDeclaration;
	private List<MethodInvocation> metodoInvocation;
	private List<ClassInstanceCreation> instanceCreation;
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
								//fieldDeclaration = new ArrayList<FieldDeclaration>();
								metodoInvocation = new ArrayList<MethodInvocation>();
								instanceCreation = new ArrayList<ClassInstanceCreation>();
								arestas = new ArrayList<Aresta>();
								
								for (TypeDeclaration classe : classVisitor.getClasses()){
									System.out.println("\n\nNome da classe: " + classe.getName());// + "  Super classe: " + classe.getSuperclassType() + "  Interface implemets: "+ classe.superInterfaceTypes() + "  teste 2: " + classe.isInterface());
									
									if(classe.getSuperclassType() != null){
										arestas.add(new ArestaHeranca(classe.getSuperclassType().toString()));
									}//aresta de herança -->>> extends
									
									int i = 0;
									while(i < classe.superInterfaceTypes().size()){
										arestas.add(new ArestaDeInterface(classe.superInterfaceTypes().get(i).toString()));
										++i;
									}//aresta de inteface --->>> implements
									
									if(classe.modifiers().size() >= 2){
										//System.out.println("teste" + classe.modifiers().get(1));
									}
									classeDeclaration = classe;
									for(FieldDeclaration declaration : declarationVisitor.getDeclarations()){
										//System.out.println("Tipo variavel : " + declaration.getType());
										if(!declaration.getType().isPrimitiveType()){
											if(!(declaration.getType().toString()).equals("String")){
												//fieldDeclaration.add(declaration);
												arestas.add(new ArestaAssociacao(declaration.getType().toString(), declaration));
											}
										}
									}// aresta de associação     
									
									for (MethodDeclaration method : methodVisitor.getMethods()){
										//System.out.println("\n\nNome do metodo: " + method.getName());// + " \nTipo de retorno do metodo: " + method.getReturnType2() + "\nParametro do metodo: " + method.parameters() + " Test: " + method.isConstructor());
										if(!method.isConstructor()){
											metodoDeclaration.add(method);
										}
									}
									
									for(ClassInstanceCreation instanceCreation : instanceVisitor.getInstances()){
										//System.out.println("\n\nInstanciação: " + instanceCreation.getType() + "Tipo (nome Classe): " + instanceCreation.resolveConstructorBinding().getName());
										arestas.add(new ArestaDeInstancia(instanceCreation.getType().toString(), instanceCreation));
									}
									
									for(MethodInvocation methodInvocation : methodInvocationVisitor.getMethodsInvocations()){
										//System.out.println("Metodo invocado : " + methodInvocation.getName() + " Pertence à classe: " + methodInvocation.resolveMethodBinding().getDeclaringClass().getName());
										
										if(!methodInvocation.resolveMethodBinding().isVarargs()){
											metodoInvocation.add(methodInvocation);
										}
										/*for(int j = 0; j < metodoDeclaration.size(); ++j){
											if(metodoDeclaration.get(j).getName().equals(methodInvocation.getName())){
												System.out.println("Metodo invocado : " + methodInvocation.getName() + " tetste: ");
												arestas.add(new ArestaChamadaDeMetodo(metodoDeclaration.get(j).getName().toString(), methodInvocation));
											}
										}*/
									}
									grafo.setGrafo(new ClasseInfo(classeDeclaration, metodoDeclaration, metodoInvocation, arestas));
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
		//testes
		/*for(int i = 0; i < grafo.getGrafo().size(); ++i){
			for(int j = 0; j < grafo.getGrafo(i).getFieldDeclaration().size(); ++j){
				for(int t = 0; t < grafo.getGrafo(i).getMethodDeclaration().size(); ++t){
					if((grafo.getGrafo(i).getFieldDeclaration(j).getType().toString()).equals(grafo.getGrafo(i).getMethodDeclaration(t).getName().toString())){
						arestas.add(null);
					}
				}
			}
		}
		
		for(int i = 0; i < grafo.getGrafo().size(); ++i){
			if(grafo.getGrafo(i).getAresta().size() != 0){
				for(int j = 0; j < grafo.getGrafo(i).getAresta().size(); ++j){
					System.out.println("No: " + grafo.getGrafo(i).getClassDeclaration().getName() + "  Aresta: " + grafo.getGrafo(i).getAresta().get(j).getNome());
				}
			}
		}*/
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
	
	/*private void arestas(){
		int i = 0;
		Aresta aresta;
		while(i < no.size()){
			if(this.no.get(i).getClassDeclaration().getSuperclassType() != null  && !this.no.get(i).getClassDeclaration().isInterface()){
				for(int j = 0; j < no.size(); ++j){
					if(this.no.get(i).getClassDeclaration().getSuperclassType().toString().equals(this.no.get(j).getClassDeclaration().getName().toString())){
						aresta = new ArestaHeranca();
						no.get(i).setAresta(aresta);
						break;
					}
				}
			}
			++i;
		}
	}*/
}
