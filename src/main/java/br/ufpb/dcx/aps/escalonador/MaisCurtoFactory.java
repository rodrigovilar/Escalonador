package br.ufpb.dcx.aps.escalonador;

public class MaisCurtoFactory implements AbstractFactory {
    @Override
    public Escalonador criarEscalonador() {
        return new MaisCurto();
    }

    @Override
    public Escalonador criarEscalonador(int quantum) {
        return new MaisCurto(quantum);
    }
}
