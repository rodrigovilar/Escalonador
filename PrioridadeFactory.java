package br.ufpb.dcx.aps.escalonador;

public class PrioridadeFactory implements AbstractFactory {
    @Override
    public Escalonador criarEscalonador() {
        return new Prioridade();
    }

    @Override
    public Escalonador criarEscalonador(int quantum) {
        return new Prioridade(quantum);
    }
}
