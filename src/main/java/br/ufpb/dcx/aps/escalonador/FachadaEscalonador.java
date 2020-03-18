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

	public String execute(Command command){
		command.setEscalonador(this.escalonador);
		return command.executar();
	}

	private AbstractFactory criarFabrica(TipoEscalonador tipoEscalonador){
		if(tipoEscalonador == TipoEscalonador.RoundRobin){
			return new RoundRobinFactory();
		}else if( tipoEscalonador == TipoEscalonador.Prioridade){
			return new PrioridadeFactory();
		}else if (tipoEscalonador == TipoEscalonador.MaisCurtoPrimeiro ) {
			return new MaisCurtoFactory();
		}else if (tipoEscalonador == TipoEscalonador.Fifo) {
			return new FifoFactory();
		}
		return null;
	}

}

