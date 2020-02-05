package br.ufpb.dcx.aps.escalonador;

public class FachadaEscalonador {

	private Escalonador escalonador;

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		if(tipoEscalonador != null){
			if(tipoEscalonador == TipoEscalonador.RoundRobin){
				escalonador = new RoundRobin();
			}else if(tipoEscalonador == TipoEscalonador.Prioridade){
				escalonador = new Prioridade();
			}
		}else {
			throw new EscalonadorException();
		}

	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
		if(roundrobin == TipoEscalonador.RoundRobin){
			escalonador = new RoundRobin(quantum);
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
