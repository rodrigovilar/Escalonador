package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;
import br.ufpb.dcx.aps.escalonador.FachadaEscalonador;

public class GetStatusCommand implements Command {

    private Escalonador escalonador;

    public GetStatusCommand(FachadaEscalonador escalonador){}

    @Override
    public String execute() {
        return escalonador.getStatus();
    }

    @Override
    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }
}
