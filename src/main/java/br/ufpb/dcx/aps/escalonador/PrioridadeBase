package br.ufpb.dcx.aps.escalonador;

public class PrioridadeBase implements CriadorTipo {
	
    @Override
    public EscalonadorBase criarEscalonador() {
        return new EscalonadorPrioridade();
    }
    @Override
    public EscalonadorBase criarEscalonador(int quantum) {
        return new EscalonadorPrioridade(quantum);
    }
}
