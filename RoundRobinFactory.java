package br.ufpb.dcx.aps.escalonador;

public class RoundRobinFactory implements AbstractFactory {

    @Override
    public Escalonador criarEscalonador() {
        return new RoundRobin();
    }

    @Override
    public Escalonador criarEscalonador(int quantum) {
        return new RoundRobin(quantum);
    }

}
