package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.EscalonadorBase;

public class TickCommand implements Command {

    private EscalonadorBase escalonadorBase;

    public TickCommand(){}

    @Override
    public String executar() {
        getEscalonador().tick();
        return null;
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