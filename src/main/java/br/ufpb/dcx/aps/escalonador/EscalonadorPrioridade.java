package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.Collections;

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
	public void tick() {

		tick = tick + (tick - (tick - 1));// Piada Interna entre os intregrantes

		if (finalizado) this.finalizandoProcesso(this.nomeFinalizado);
			this.finalizado = false;

		if (processoBloqueado)
			this.bloqueandoProcesso(nomeProcessoBloquado);

		if (retomar) this.retomandoProcesso(listaDeProcessosRetomados);
			this.retomar = false; Collections.sort(ListaDeProcessos);
		
		if (rodando != null) rodando.addtempo();

		if (rodando != null && rodando.getAtual() >= quantum && Proximo < tick)
			this.trocaParaFila();

		if (this.rodando == null && !ListaDeProcessos.isEmpty())
			this.adicionarProcessoRodando();

		if ( retomar == false && !listaDeProcessosBloqueados.isEmpty()) {
			this.processoBloqueado = true; this.trocaParaFila();
			this.adicionarProcessoRodando();
		}

		if (!ListaDeProcessos.isEmpty() && ListaDeProcessos.get(0).getPrioridade() < rodando.getPrioridade()) {
			if (rodando != null) this.trocaParaFila(); this.adicionarProcessoRodando();
				Collections.sort(ListaDeProcessos);		
		}
	}

}
