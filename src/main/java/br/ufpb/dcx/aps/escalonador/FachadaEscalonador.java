package br.ufpb.dcx.aps.escalonador;

public class FachadaEscalonador {

	public EscalonadorFactory factory;
	private Escalonador escalonador;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		if(tipoEscalonador != null){
			if(tipoEscalonador == TipoEscalonador.RoundRobin){
				factory = new RoundRobinFactory();
				escalonador = factory.criarEscalonador();
			}else if(tipoEscalonador == TipoEscalonador.Prioridade){
				factory = new PrioridadeFactory();
				escalonador = factory.criarEscalonador();
			}
		}else {
			throw new EscalonadorException();
		}
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
		if(roundrobin == TipoEscalonador.RoundRobin){
			factory = new RoundRobinFactory();
			escalonador = factory.criarEscalonador(quantum);
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
}
