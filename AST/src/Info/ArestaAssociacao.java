package Info;

import org.eclipse.jdt.core.dom.FieldDeclaration;

public class ArestaAssociacao extends Aresta{
	
	private FieldDeclaration variavel;
	
	public ArestaAssociacao(String tipoClasse, FieldDeclaration variavel){
		super(tipoClasse);
		this.variavel = variavel;
	}

	public FieldDeclaration getNomeVariavel() {
		return variavel;
	}

	public void setNomeVariavel(FieldDeclaration nomeVariavel) {
		this.variavel = nomeVariavel;
	}
}
