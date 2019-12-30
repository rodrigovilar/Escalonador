package br.ufpb.dcx.aps.escalonador;

public class Processo {

	private String nome;
	private int tickRodando;
	private int tickInicial;
	private int ticks;

	
	public Processo(String nome, int tickCriacao) {
		this.nome = nome;
		this.tickRodando = 0;
		this.tickInicial = tickCriacao;
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
	
}
