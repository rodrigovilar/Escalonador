package br.ufpb.dcx.aps.escalonador;

public interface EscalonadorFactory {
    public Escalonador criarEscalonador();
    public Escalonador criarEscalonador(int quantum);
}
