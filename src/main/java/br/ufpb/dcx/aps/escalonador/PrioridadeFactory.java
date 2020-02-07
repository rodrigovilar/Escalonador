package br.ufpb.dcx.aps.escalonador;

public class PrioridadeFactory implements EscalonadorFactory {
    @Override
    public Escalonador criarEscalonador() {
        return new Prioridade();
    }

    @Override
    public Escalonador criarEscalonador(int quantum) {
        return null;
    }
}
