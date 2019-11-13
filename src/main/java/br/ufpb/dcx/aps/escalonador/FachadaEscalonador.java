package br.ufpb.dcx.aps.escalonador;

import java.util.ArrayList;
import java.util.List;

public class FachadaEscalonador {
	
	private int tick;
	private List<Processo> fila;
	private String status; 
	private String processos= "";
	
	public FachadaEscalonador(TipoEscalonador tipoEscalonador) {
		this.fila = new ArrayList<Processo>();
	}

	public FachadaEscalonador(TipoEscalonador roundrobin, int quantum) {
	}

	public String getStatus() {
		status =  "Escalonador RoundRobin;Processos: {"+ processos + "};Quantum: 3;Tick: " + tick;
		return status;
	}

	public void tick() {
		tick++;
		checaStatusProcessos();
	}

	public void adicionarProcesso(String nomeProcesso) {
		Processo processo = new Processo();
		processo.setNome(nomeProcesso);
		
		fila.add(processo);
		
		processos += processo.getStatus()+": [" + nomeProcesso + "]";
	}
	
	public void checaStatusProcessos() {
		for (Processo processo: fila) {
			processo.setStatus("Rodando");
		}
		
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
