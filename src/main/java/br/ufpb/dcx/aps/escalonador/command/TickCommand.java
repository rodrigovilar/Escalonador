package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;
import br.ufpb.dcx.aps.escalonador.FachadaEscalonador;

public class TickCommand implements Command {

    private Escalonador escalonador;

    public TickCommand(Escalonador escalonador){
        this.escalonador = escalonador;
    }

    @Override
    public void execute() {
        escalonador.tick();
    }

}
