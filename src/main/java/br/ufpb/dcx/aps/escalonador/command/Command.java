package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.*;

public interface Command {

    String executar();
    EscalonadorBase getEscalonador();
    void setEscalonador(EscalonadorBase escalonadorBase);
}