package br.ufpb.dcx.aps.escalonador;

public class Processo {
	private String nome;
	private Status status;
	private int tickRodado;

	
	public Processo(String nome) {
		this.nome = nome;
		this.status= status.FILA;
		this.tickRodado = 0;
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getTickRodando() {
		return tickRodado;
	}

	public void setTickRodando(int tick) {
		this.tickRodado = tick;
	}
	
	public void addTickRodando() {
		this.tickRodado++;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
}