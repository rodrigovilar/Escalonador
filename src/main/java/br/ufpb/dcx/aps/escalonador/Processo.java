package br.ufpb.dcx.aps.escalonador;

public class Processo implements Comparable<Processo> {

	private int prioridade;
	private String nome;
	private int tickRodando;
	private int tickInicial;
	private int ticks;
	private int tempo;

	public Processo(String nome, int tickCriacao) {
		this.nome = nome;
		this.tickRodando = 0;
		this.tickInicial = tickCriacao;
	}

	public Processo(String nome, int tickCriacao, int prioridade) {
		this.prioridade = prioridade;
		this.nome = nome;
		this.tickRodando = 0;
		this.tickInicial = tickCriacao;
	}

	public Processo(int tempo, int tickCriacao, String nome) {
		this.tempo = tempo;
		this.nome = nome;
		this.tickRodando = 0;
		this.tickInicial = tickCriacao;
	}

	public int getTemp() {
		return tempo;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTickRodando() {
		return tickRodando;
	}

	public void setTickRodando(int tick) {
		this.tickRodando = tick;
	}

	public void addTickRodando() {
		this.tickRodando++;
	}

	public int getTickInicial() {
		return tickInicial;
	}

	public void setTickInicial(int tickCriacao) {
		this.tickInicial = tickCriacao;
	}

	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	@Override
	public int compareTo(Processo outroProcesso) {
		// TODO Auto-generated method stub
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

}