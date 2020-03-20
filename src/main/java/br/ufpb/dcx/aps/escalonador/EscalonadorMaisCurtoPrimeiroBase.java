package br.ufpb.dcx.aps.escalonador;

public class EscalonadorMaisCurtoPrimeiroBase implements CriadorTipo {
	
	@Override
	public EscalonadorBase criarEscalonador() {
		return new EscalonadorMaisCurtoPrimeiro();
	}
	@Override
	public EscalonadorBase criarEscalonador(int quantum) {
		return new EscalonadorMaisCurtoPrimeiro(quantum);
	}
}
