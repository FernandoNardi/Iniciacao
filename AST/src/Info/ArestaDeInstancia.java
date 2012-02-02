package Info;

import org.eclipse.jdt.core.dom.ClassInstanceCreation;

public class ArestaDeInstancia extends Aresta{
	
	ClassInstanceCreation instancia;

	public ArestaDeInstancia(String tipo, ClassInstanceCreation instancia) {
		super(tipo);
		this.instancia = instancia;
	}

	public ClassInstanceCreation getInstancia() {
		return instancia;
	}

	public void setInstancia(ClassInstanceCreation instancia) {
		this.instancia = instancia;
	}
	
}
