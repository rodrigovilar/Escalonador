package br.ufpb.dcx.aps.escalonador;

public interface AbstractFactory {
    public Escalonador criarEscalonador();
    public Escalonador criarEscalonador(int quantum);
}
