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
	public void trocaParaFila() {

		boolean bloqueado = false;

		for (Processo processos : listaDeProcessosBloqueados) {
			if (rodando.getNome().equals(processos.getNome())) bloqueado = true; break;		
		}

		if (rodando != null && bloqueado == false) {
			if (!ListaDeProcessos.isEmpty() && rodando.getPrioridade() < ListaDeProcessos.get(0).getPrioridade())
				return;

			rodando.setAtual(0); 
			ListaDeProcessos.add(rodando);
			rodando = null;
			
		} else if (rodando != null && bloqueado) rodando.setAtual(0); rodando = null;	
	
	}

	public void finalizandoProcesso(String nomeProcesso) {

		if (this != null) {
			if (rodando.getNome().equals(nomeProcesso)) {
				rodando = null;

			} else {		
				for (Processo processo : ListaDeProcessos) {
					if (nomeProcesso.equals(processo.getNome())) 
						ListaDeProcessos.remove(processo); break;
				}
			}
		}
	}

	public void bloqueandoProcesso(String nomeProcessoBloqueado) {

		this.processoBloqueado = false;
		if (this != null && nomeProcessoBloqueado.equals(rodando.getNome()))
			listaDeProcessosBloqueados.add(rodando); 
			this.nomeProcessoBloquado = rodando.getNome();		
	}

	public void retomandoProcesso(List<Processo> retomado) {

		while (!retomado.isEmpty()) {

			for (int i = 0; i < listaDeProcessosBloqueados.size(); i++) {
				for (int k = 0; k < retomado.size(); k++) {

					if (listaDeProcessosBloqueados.get(i).getNome().equals(retomado.get(k).getNome())) {
						ListaDeProcessos.add(listaDeProcessosBloqueados.get(i));
						listaDeProcessosBloqueados.remove(i);
						retomado.remove(k);
					}
				}
			}
		}
	}

}
