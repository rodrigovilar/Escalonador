package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;

public interface Command {

    String executar();
    Escalonador getEscalonador();
    void setEscalonador(Escalonador escalonador);
}
