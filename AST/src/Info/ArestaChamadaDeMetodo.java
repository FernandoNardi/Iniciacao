package Info;

import org.eclipse.jdt.core.dom.MethodInvocation;

public class ArestaChamadaDeMetodo extends Aresta{

	private MethodInvocation metodoChamado;
	//private String metodoLocalChamado; //em qual metodo houve a chamada
	
	public ArestaChamadaDeMetodo(String nomeClasse, MethodInvocation metodoChamado){//, String metodoLocalChamado){
		super(nomeClasse);
		this.metodoChamado = metodoChamado;
		//this.metodoLocalChamado = metodoLocalChamado;
	}

	public MethodInvocation getMetodoChamado() {
		return this.metodoChamado;
	}

	public void setMetodoChamado(MethodInvocation metodoChamado) {
		this.metodoChamado = metodoChamado;
	}

	/*
	public String getMetodoLocalChamado() {
		return metodoLocalChamado;
	}

	public void setMetodoLocalChamado(String metodoLocalChamado) {
		this.metodoLocalChamado = metodoLocalChamado;
	}*/
	
}
