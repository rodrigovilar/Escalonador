package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;

public class EscalonadorPrioridade extends EscalonadorBase {
	public EscalonadorPrioridade() {
		
		this.ListaDeProcessos = new ArrayList<>();
		this.tipoEscalonador = TipoEscalonador.Prioridade;
		this.listaDeProcessosBloqueados = new ArrayList<>();
		this.listaDeProcessosRetomados = new ArrayList<>();
		this.quantum = 3;
	}

	public EscalonadorPrioridade(int quantum) {

		if (quantum <= 0)
			throw new EscalonadorException();

		if (rodando != null)
			throw new EscalonadorException();
		
		this.ListaDeProcessos = new ArrayList<>();
		this.tipoEscalonador = TipoEscalonador.Prioridade;
		this.listaDeProcessosBloqueados = new ArrayList<>();
		this.quantum = quantum;
	}

}
