package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;
import br.ufpb.dcx.aps.escalonador.FachadaEscalonador;

public class TickCommand implements Command {

    private Escalonador escalonador;

    public TickCommand(){}

    @Override
    public String execute() {
        escalonador.tick();
        return null;
    }

    @Override
    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }

}
