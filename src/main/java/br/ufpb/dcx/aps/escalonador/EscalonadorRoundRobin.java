package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class EscalonadorRoundRobin extends EscalonadorBase {

	public EscalonadorRoundRobin() {
		
		ListaDeProcessos = new ArrayList<>();
		tipoEscalonador = TipoEscalonador.RoundRobin;
		listaDeProcessosBloqueados = new ArrayList<>();
		listaDeProcessosRetomados = new ArrayList<>();
		this.quantum = 3;
	}

	public EscalonadorRoundRobin(int quantum) {
		
		if (quantum <= 0) 
			throw new EscalonadorException();
		
		if (rodando != null) 
			throw new EscalonadorException();
		
		ListaDeProcessos = new ArrayList<>();
		tipoEscalonador = TipoEscalonador.RoundRobin;
		listaDeProcessosBloqueados = new ArrayList<Processo>();
		this.quantum = quantum;
	}

public void tick() {
		
		tick = tick + (tick - (tick - 1));//Piada Interna entre os intregrantes

		if (finalizado) this.finalizandoProcesso(this.nomeFinalizado);
			finalizado = false;
	
		if (processoBloqueado) this.bloqueandoProcesso(nomeProcessoBloquado);
		
		if (retomar) this.retomandoProcesso(listaDeProcessosRetomados);
			retomar = false;
		
		if (rodando != null) this.rodando.addtempo();
		
		if (rodando != null && rodando.getAtual() >= quantum && Proximo < tick) 
			this.trocaParaFila();	

		if (this.rodando == null && !ListaDeProcessos.isEmpty())
			this.adicionarProcessoRodando();	

		if (!listaDeProcessosBloqueados.isEmpty() && retomar == false) {
			this.processoBloqueado = true;
			this.trocaParaFila();
			this.adicionarProcessoRodando();
		}
	}

	public void trocaParaFila() {
		
		boolean bloqueado = false;

		for (Processo processos : listaDeProcessosBloqueados) {
			if (rodando.getNome().equals(processos.getNome())) {
				bloqueado = true; break;
			}
		}
		
		if (rodando != null && bloqueado == false) {
			this.rodando.setAtual(0);
			this.ListaDeProcessos.add(rodando);
			this.rodando = null;
			
		} else if (rodando != null && bloqueado) this.rodando.setAtual(0);
			this.rodando = null;	
	}

	public void finalizandoProcesso(String nomeProcesso) {
		
		if (rodando != null) {
			if (rodando.getNome().equals(nomeProcesso)) {
				rodando = null;
				
			} else {
				for (Processo processos : ListaDeProcessos) {
					if (processos.getNome().equals(nomeProcesso)) 
						ListaDeProcessos.remove(processos); break;			
				}
			}
		}
	}

	public void bloqueandoProcesso(String processoBloqueado) {
		
		this.processoBloqueado = false;
		
		if (rodando != null && processoBloqueado.equals(rodando.getNome())) {
			listaDeProcessosBloqueados.add(rodando);
			this.nomeProcessoBloquado = rodando.getNome();		
		}
	}

	public void retomandoProcesso(List<Processo> retomado) {

		while (!retomado.isEmpty()) {
			
			for (int i = 0; i < listaDeProcessosBloqueados.size(); i++) {
				
				for (int k = 0; k < retomado.size(); k++) {
					
					if (retomado.get(k).getNome().equals(listaDeProcessosBloqueados.get(i).getNome())) {
						ListaDeProcessos.add(listaDeProcessosBloqueados.get(i));
						listaDeProcessosBloqueados.remove(i);
						retomado.remove(k);
					}
				}
			}
		}
	}
}
