package Info;

import org.eclipse.jdt.core.dom.MethodInvocation;

public class ArestaChamadaDeMetodo extends Aresta{

	private MethodInvocation metodoChamado;
	
	public ArestaChamadaDeMetodo(String nomeClasse, MethodInvocation metodoChamado){
		super(nomeClasse);
		this.metodoChamado = metodoChamado;
	}

	public MethodInvocation getMetodoChamado() {
		return this.metodoChamado;
	}

	public void setMetodoChamado(MethodInvocation metodoChamado) {
		this.metodoChamado = metodoChamado;
	}

	@Override
	public Object getObject() {
		return this.metodoChamado;
	}
}
