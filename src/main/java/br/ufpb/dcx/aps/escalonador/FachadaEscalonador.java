package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;

public class FachadaEscalonador {
	
	private int tick;
	private ArrayList<String> fila = new ArrayList<String>();

	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
	}

	public String getStatus() {
		if(this.fila.isEmpty()) {
			return "Escalonador RoundRobin;Processos: {};Quantum: 3;Tick: " + this.tick;
		} else if((this.tick == 0) && (!fila.isEmpty()) ) {
			return "Escalonador RoundRobin;Processos: {Fila: " + this.fila.toString() + "};Quantum: 3;Tick: " + this.tick;
		} else if((this.tick > 0) && (!fila.isEmpty()) ) {
			return "Escalonador RoundRobin;Processos: {Rodando: " + this.fila.get(0) + "};Quantum: 3;Tick: " + this.tick;
		} else  {
			return null;
		}
	}

	public void tick() {
		tick++;
	}

	public void adicionarProcesso(String nomeProcesso) {
		this.fila.add(nomeProcesso);
	}

	public void adicionarProcesso(String nomeProcesso, int prioridade) {
	}

	public void finalizarProcesso(String nomeProcesso) {
	}

	public void bloquearProcesso(String nomeProcesso) {
	}

	public void retomarProcesso(String nomeProcesso) {
		
	}

	public void adicionarProcessoTempoFixo(String string, int duracao) {
		
	}
}
