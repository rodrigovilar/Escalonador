package br.ufpb.dcx.aps.escalonador;

public class RoundRobinBase implements CriadorTipo {

    @Override
    public EscalonadorBase criarEscalonador() {
        return new EscalonadorRoundRobin();
    }
    @Override
    public EscalonadorBase criarEscalonador(int quantum) {
        return new EscalonadorRoundRobin(quantum);
    }
}
