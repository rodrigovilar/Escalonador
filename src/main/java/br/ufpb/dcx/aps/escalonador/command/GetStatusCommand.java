package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.*;

public class GetStatusCommand implements Command {

    private EscalonadorBase escalonadorBase;

    public GetStatusCommand(){}

    @Override
    public String executar() {
        return this.getEscalonador().getStatus();
    }
    @Override
    public EscalonadorBase getEscalonador() {
        return this.escalonadorBase;
    }
    @Override
    public void setEscalonador(EscalonadorBase escalonadorBase) {
        this.escalonadorBase = escalonadorBase;
    }
}