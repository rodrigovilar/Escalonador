package br.ufpb.dcx.aps.escalonador;

public class FachadaEscalonador {

	private AbstractFactory factory;
	private Escalonador escalonador;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		if(tipoEscalonador != null){
			factory = criarFabrica(tipoEscalonador);
			escalonador = factory.criarEscalonador();
		}else {
			throw new EscalonadorException();
		}
	}

	public FachadaEscalonador(TipoEscalonador tipoEscalonador, int quantum) {
		if( tipoEscalonador != null && quantum > 0) {
			factory = criarFabrica(tipoEscalonador);
			escalonador = factory.criarEscalonador(quantum);
		}else {
			throw new EscalonadorException();
		}
	}

	public String getStatus() {
		return escalonador.getStatus();
	}

	public void tick() {
		escalonador.tick();
	}

	public void adicionarProcesso(String nomeProcesso) {
		escalonador.adicionarProcesso(nomeProcesso);
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
		escalonador.adicionarProcesso(nomeProcesso, prioridade);
	}

	public void finalizarProcesso(String nomeProcesso) {
		escalonador.finalizarProcesso(nomeProcesso);
	}

	public void bloquearProcesso(String nomeProcesso) {
		escalonador.bloquearProcesso(nomeProcesso);
	}

	public void retomarProcesso(String nomeProcesso) {
		escalonador.retomarProcesso(nomeProcesso);
	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
		escalonador.adicionarProcessoTempoFixo(string, duracao);
	}


	public AbstractFactory criarFabrica(TipoEscalonador tipoEscalonador){
		if(tipoEscalonador == TipoEscalonador.RoundRobin){
			return new RoundRobinFactory();
		}else if( tipoEscalonador == TipoEscalonador.Prioridade){
			return new PrioridadeFactory();
		}
		return null;
	}
}

