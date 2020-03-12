package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public interface Command {
    String execute();

    void setEscalonador(Escalonador escalonador);
}
