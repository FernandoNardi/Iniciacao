package ASTSimple.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import Info.*;

public class GrafoVisitor {
	
	private RandomAccessFile canal;
	private String string = "digraph G\n{\n";
	private Aresta a;
	private String no, no1, aresta;
	
	public GrafoVisitor(GrafoDiagrama grafo){//Abre o arquivo ou cria(caso não tenha arquivo)
		
		try{
			
			for(int i = 0; i < grafo.getGrafo().size(); ++i){
				no = grafo.getGrafo().get(i).getClassDeclaration().getName().toString();
				int j = 0;
				while(j < grafo.getGrafo().get(i).getAresta().size()){
					string = string + no;
					a = grafo.getGrafo().get(i).getAresta().get(j);
					if(a instanceof ArestaAssociacao){
						no1 = grafo.getGrafo().get(i).getAresta().get(j).getNome();
						aresta = "[label=\"associação\"];\n";
					}
					else if(a instanceof ArestaChamadaDeMetodo){
						no1 = grafo.getGrafo().get(i).getAresta().get(j).getNome();
						aresta = "[label=\"ChamadaDemetodo\"];\n"; 
					}
					else if(a instanceof ArestaDeInstancia){
						no1 = grafo.getGrafo().get(i).getAresta().get(j).getNome();
						aresta = "[label=\"Instancia\"];\n"; 
					}
					else if(a instanceof ArestaDeInterface){
						no1 = grafo.getGrafo().get(i).getAresta().get(j).getNome();
						aresta = "[label=\"Implements\"];\n"; 
					}
					else if(a instanceof ArestaHeranca){
						no1 = grafo.getGrafo().get(i).getAresta().get(j).getNome();
						aresta = "[label=\"Extends\"];\n"; 
					}
					string = string + " -> " + no1 + aresta;
					++j;
				}
				if(grafo.getGrafo().get(i).getAresta().size() == 0){
					string = string + no + ";\n";
				}
			}
			string = string + "}";
			
			//canal = new RandomAccessFile("C:/Users/Fernando/git/AST/AST/Gravacao_Arquivo.txt", "rw");
			//canal.writeChars(string);
			//canal.close();
			BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/Fernando/git/AST/AST/Gravacao_Arquivo.txt"));
	        out.write(string);
	        out.close();
			
		}
		catch(IOException exception){
			exception.printStackTrace();
			System.exit(1);
		}
	}
}
