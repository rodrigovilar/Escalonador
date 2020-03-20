package br.ufpb.dcx.aps.escalonador;

public interface CriadorTipo {
	public EscalonadorBase criarEscalonador();

	public EscalonadorBase criarEscalonador(int quantum);
}