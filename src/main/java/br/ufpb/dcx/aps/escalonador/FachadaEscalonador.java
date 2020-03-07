package br.ufpb.dcx.aps.escalonador;

import br.ufpb.dcx.aps.escalonador.command.*;

public class FachadaEscalonador {

	private Escalonador escalonador;
	private AbstractFactory factory;

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
		escalonador.execute(new TickCommand(escalonador));
	}

	public void adicionarProcesso(String nomeProcesso) {
		escalonador.execute(new AdicionarProcessoCommand(escalonador, nomeProcesso));
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
		escalonador.execute(new AdicionarProcessoCommand(escalonador, nomeProcesso, prioridade));
	}

	public void finalizarProcesso(String nomeProcesso) {
		escalonador.execute(new FinalizarProcessoCommand(escalonador, nomeProcesso));
	}

	public void bloquearProcesso(String nomeProcesso) {
		escalonador.execute(new BloquearProcessoCommand(escalonador, nomeProcesso));
	}

	public void retomarProcesso(String nomeProcesso) {
		escalonador.execute(new RetomarProcessoCommand(escalonador, nomeProcesso));
	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
		escalonador.execute(new AdicionarProcessoTempoFixo(escalonador, string, duracao));
	}

	private AbstractFactory criarFabrica(TipoEscalonador tipoEscalonador){
		if(tipoEscalonador == TipoEscalonador.RoundRobin){
			return new RoundRobinFactory();
		}else if( tipoEscalonador == TipoEscalonador.Prioridade){
			return new PrioridadeFactory();
		}else if (tipoEscalonador == TipoEscalonador.MaisCurtoPrimeiro ) {
			return new MaisCurtoFactory();
		}
		return null;
	}
}

