package br.ufpb.dcx.aps.escalonador;

public class Processo implements Comparable<Processo> {

	private int prioridade,atual,inicial,ticks,tempo;
	private String nomeProcesso;
	
	public Processo(String nome, int inicio) {
		
		this.inicial = inicio;
		this.atual = 0;
		this.nomeProcesso = nome;
	}

	public Processo(String nome, int inicio, int prioridade) {
		
		this.inicial = inicio;
		this.atual = 0;
		this.nomeProcesso = nome;
		this.prioridade = prioridade;
	}

	public Processo(int tempo, int inicio, String nome) {
		
		this.inicial = inicio;
		this.atual = 0;
		this.tempo = tempo;
		this.nomeProcesso = nome;
	}

	@Override
	public String toString() {
		return this.nomeProcesso;
	}

	@Override
	public int compareTo(Processo outroProcesso) {
		
		if(this.prioridade < outroProcesso.prioridade) {
			return -1;
			
		}else if(this.prioridade > outroProcesso.prioridade) {
			return 1;
		}

		if(this.tempo < outroProcesso.tempo) {
			return -1;
			
		}else if(this.tempo > outroProcesso.tempo) {
			return 1;
		}
		return 0;
	}

	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getAtual() {
		return atual ;
	}
	public void setAtual(int tick) {
		this.atual = tick;
	}
	public void addtempo() {
		this.atual++;
	}
	public int getInicial() {
		return inicial;
	}
	public void setInicial(int inicio) {
		this.inicial = inicio;
	}
	public int getTicks() {
		return ticks;
	}
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
	public String getNome() {
		return nomeProcesso;
	}
	public void setNome(String nome) {
		this.nomeProcesso = nome;
	}
	public int getTempo() {
		return tempo;
	}
}
