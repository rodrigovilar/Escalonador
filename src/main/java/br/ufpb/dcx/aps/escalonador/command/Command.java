package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public abstract class Command {

    private Escalonador escalonador;

    public abstract String executar();

    public Escalonador getEscalonador() {
        return escalonador;
    }

    public void setEscalonador(Escalonador escalonador){
        this.escalonador = escalonador;
    }
}
