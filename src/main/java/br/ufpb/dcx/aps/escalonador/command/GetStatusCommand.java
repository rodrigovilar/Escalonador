package br.ufpb.dcx.aps.escalonador.command;

import br.ufpb.dcx.aps.escalonador.Escalonador;
import br.ufpb.dcx.aps.escalonador.FachadaEscalonador;
import br.ufpb.dcx.aps.escalonador.TipoEscalonador;

public class GetStatusCommand implements Command {

    private Escalonador escalonador;

    public GetStatusCommand(){}

    @Override
    public String executar() {
        return this.getEscalonador().getStatus();
    }

    @Override
    public Escalonador getEscalonador() {
        return this.escalonador;
    }

    @Override
    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }

}
